/*
 * Copyright 2015 www.hyberbin.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Email:hyberbin@qq.com
 */
package cn.com.ktm.mt.model.util.excel.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.ktm.mt.model.util.excel.annotation.ExcelColumnGroup;
import cn.com.ktm.mt.model.util.excel.annotation.ExcelVoConfig;
import cn.com.ktm.mt.model.util.excel.exception.AdapterException;
import cn.com.ktm.mt.model.util.excel.exception.ColumnErrorException;
import cn.com.ktm.mt.model.util.excel.exception.ExcelVoErrorException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import cn.com.ktm.mt.model.util.excel.adapter.IOAdapter;
import cn.com.ktm.mt.model.util.excel.bean.BaseExcelVo;
import cn.com.ktm.mt.model.util.excel.bean.ColumnBean;
import cn.com.ktm.mt.model.util.excel.bean.DataBean;
import cn.com.ktm.mt.model.util.excel.bean.FieldBean;
import cn.com.ktm.mt.model.util.excel.bean.FieldType;
import cn.com.ktm.mt.model.util.excel.bean.GroupConfig;
import cn.com.ktm.mt.model.util.excel.bean.TookPairs;
import cn.com.ktm.mt.model.util.excel.json.JsonUtil;
import cn.com.ktm.mt.model.util.excel.language.ILanguage;
import cn.com.ktm.mt.model.util.excel.language.SimpleLanguage;
import cn.com.ktm.mt.model.util.excel.utils.AdapterUtil;
import cn.com.ktm.mt.model.util.excel.utils.DicCodePool;
import cn.com.ktm.mt.model.util.excel.utils.Message;
import cn.com.ktm.mt.model.util.excel.utils.ObjectHelper;
import cn.com.ktm.mt.model.util.excel.utils.Reflections;

/**
 * ????????? ?????????????????? ?????????????????? ????????????????????????????????? ???????????????0??????????????????????????????????????????????????? User: Hyberbin Date:
 * 13-12-3 Time: ??????4:03
 * @param <T>
 */
public class ExportExcelService<T extends BaseExcelVo> extends BaseExcelService {

    protected final List<T>       dataList;
    protected final Class         voClass;
    protected final Sheet         sheet;
    protected final ExcelVoConfig config;
    protected final String[]      fields;
    protected final DataBean dataBean;
    protected       int           columnLength;
    protected final DicCodePool dicCodePool;
    protected final IOAdapter outputFactory;
    protected final Method        defaultAdapterMethod;
    protected final List<BaseExcelVo> errorList = new ArrayList<BaseExcelVo>();
    protected final String title;
    protected Map<String, GroupConfig> groupConfig = new HashMap<String, GroupConfig>();
    protected Map<String, TookPairs>   tookMap     = new HashMap<String, TookPairs>();
    /** ???????????? */
    private ILanguage language    = new SimpleLanguage();

    /**
     * ?????????????????? (??????????????????)
     *
     * @param data
     * @param sheet
     * @param fields
     * @param title
     * @throws Exception
     */
    public ExportExcelService(Object data, Sheet sheet, String[] fields, String title) throws Exception {
        if (data instanceof Class) {
            voClass = (Class) data;
            dataList = null;
        } else if (List.class.isAssignableFrom(data.getClass())) {
            voClass = ((List) data).get(0).getClass();
            dataList = (List<T>) data;
        } else {
            throw new ExcelVoErrorException(data.getClass(), Message.VO_INSTANCE_ERROR);
        }
        this.sheet = sheet;
        this.title = title;
        if (voClass.isAnnotationPresent(ExcelVoConfig.class)) {
            config = (ExcelVoConfig) voClass.getAnnotation(ExcelVoConfig.class);
        } else {
            throw new ExcelVoErrorException(voClass, Message.EXCEL_VO_ERROR);
        }
        List<Field> fieldList = new ArrayList<Field>(0);
        for (String field : fields) {
            Field accessibleField = Reflections.getAccessibleField(voClass, field);
            if (accessibleField != null) {
                fieldList.add(accessibleField);
            } else {
                log.info("{}??????????????????", field);
            }
        }
        dataBean = new DataBean(fieldList, config.inputFactory(), config.outputFactory(), config.validateClass());
        this.fields = dataBean.getFiledNames();
        Class factoryClass = config.outputFactory();
        log.debug("??????????????????????????????{}", factoryClass.getName());
        Constructor constructor = factoryClass.getConstructor(AdapterUtil.Constructor);
        dicCodePool = new DicCodePool();
        outputFactory = (IOAdapter) constructor.newInstance(dicCodePool);
        log.debug("????????????????????????");
        defaultAdapterMethod = AdapterUtil.getDefaultAdapterMethod(factoryClass);
        if (defaultAdapterMethod == null) {
            throw new IllegalArgumentException("?????????????????????????????????");
        }
        log.debug("????????????????????????{}", defaultAdapterMethod.getName());
    }

    /**
     * ?????????????????????took ???sourceField???????????????distField????????????
     * @param sourceField
     * @param distField
     * @param innerIndex ??????????????????
     * @param value ???????????????
     * @return
     */
    public ExportExcelService addTook(String sourceField, String distField, int innerIndex, String value) {
        tookMap.put(distField + innerIndex, new TookPairs(sourceField, distField, value));
        return this;
    }

    /** *
     * ?????????????????????took ???sourceField???????????????distField????????????
     * @param sourceField
     * @param distField
     * @param value ???????????????
     * @return
     */
    public ExportExcelService addTook(String sourceField, String distField, String value) {
        tookMap.put(distField + 0, new TookPairs(sourceField, distField, value));
        return this;
    }

    /**
     * ??????????????????(??????????????????)
     *
     * @param data
     * @param sheet
     * @param title
     * @throws Exception
     */
    public ExportExcelService(Object data, Sheet sheet, String title) throws Exception {
        this(data, sheet, Reflections.getAllFieldNames(data instanceof Class ? data : ((List) data).get(0).getClass(), BaseExcelVo.class).toArray(new String[]{}), title);
    }

    public ExportExcelService addDic(String name, List<Map> maps) {
        dicCodePool.addMap(name, maps);
        return this;
    }

    public ExportExcelService addDic(String name, Map<String, String> map) {
        dicCodePool.addMap(name, map);
        return this;
    }

    public ExportExcelService addDic(String name, String key, String value) {
        dicCodePool.addMap(name, key, value);
        return this;
    }

    /**
     * ????????????
     * @return
     */
    private int createHead() {
        List<String> columnBeanJson = new ArrayList<String>(0);
        List<String> columns = new ArrayList<String>();
        for (Field field : dataBean.getFiledList()) {
            if (field.isAnnotationPresent(ExcelColumnGroup.class)) {
                ExcelColumnGroup columnGroup = field.getAnnotation(ExcelColumnGroup.class);
                GroupConfig group = groupConfig.get(field.getName());
                if (!BaseExcelVo.class.isAssignableFrom(columnGroup.type())) {//List?????????????????????
                    for (int i = 0; i < group.getLength(); i++) {
                        ColumnBean columnBean = new ColumnBean();
                        columnBean.setColumnName(field.getName());
                        columnBean.setSize(1);
                        columnBean.setLength(group.getLength());
                        TookPairs tookPairs = tookMap.get(field.getName() + i);
                        if (tookPairs != null) {
                            String took = tookPairs.getValue();
                            if (!ObjectHelper.isNullOrEmptyString(took)) {
                                columnBean.setTookValue(took);
                            }
                        }
                        columnBeanJson.add(JsonUtil.toJSON(columnBean));
                        columns.add(group.getLangName(0, i));
                    }
                } else {//List????????????????????????
                    if (ObjectHelper.isEmpty(group.getFieldNames())) {
                        String[] filedNames = dataBean.getChildDataBean(field.getName()).getFiledNames();
                        group.setFieldNames(Arrays.asList(filedNames));
                    }
                    for (int i = 0; i < group.getLength(); i++) {
                        for (int j = 0; j < group.getFieldNames().size(); j++) {
                            ColumnBean columnBean = new ColumnBean();
                            columnBean.setColumnName(field.getName());
                            columnBean.setSize(group.getGroupSize());
                            columnBean.setLength(group.getLength());
                            columnBean.setInnerColumn(group.getFieldNames().get(j));
                            TookPairs tookPairs = tookMap.get(columnBean.getInnerColumn() + i);
                            if (tookPairs != null && !ObjectHelper.isNullOrEmptyString(tookPairs.getValue())) {
                                columnBean.setTookName(tookPairs.getSourceField());
                                columnBean.setTookValue(tookPairs.getValue());
                            }
                            columnBeanJson.add(JsonUtil.toJSON(columnBean));
                            columns.add(group.getLangName(j, i));
                        }
                    }
                }
            } else {
                ColumnBean columnBean = new ColumnBean();
                columnBean.setColumnName(field.getName());
                TookPairs tookPairs = tookMap.get(field.getName() + 0);
                if (tookPairs != null && !ObjectHelper.isNullOrEmptyString(tookPairs.getValue())) {
                    columnBean.setTookValue(tookPairs.getValue());
                }
                columnBeanJson.add(JsonUtil.toJSON(columnBean));
                columns.add(language.translate(field));
            }
        }
        sheet.createRow(HASH_ROW);
        addTitle(sheet, TITLE_ROW, columnBeanJson.size(), title);
        Row row = addRow(sheet, HIDDEN_FIELD_HEAD, columnBeanJson.toArray(new String[]{}));
        addRow(sheet, COLUMN_ROW, columns.toArray(new String[]{}));
        row.setHeight(Short.valueOf("0"));
        return columnBeanJson.size();
    }

    public ExportExcelService exportTemplate() throws Exception {
        ValidateExcelService validateExcelService = new ValidateExcelService(voClass, sheet, fields, dicCodePool, dataBean);
        validateExcelService.groupConfig = groupConfig;
        validateExcelService.doValidate();
        return this;
    }

    public ExportExcelService doExport() throws AdapterException, ColumnErrorException {
        log.debug("???????????????????????????");
        columnLength = createHead();
        log.debug("??????????????????,???????????????{}???", columnLength);
        int dataRowIndex = START_ROW;
        long hashVal = 0;
        if (ObjectHelper.isNotEmpty(dataList)) {
            for (T t : dataList) {
                Row rowdata = createRow(sheet, dataRowIndex, columnLength);
                int cloIndex = 0;
                for (String field : fields) {
                    FieldBean fieldBean = dataBean.getFieldBean(field);
                    if (fieldBean.getFieldType() == FieldType.BASIC) {
                        getSimpleField(fieldBean, t, rowdata, dataRowIndex, cloIndex, dataBean);
                        cloIndex++;
                    } else if (fieldBean.getFieldType() == FieldType.BAS_ARRAY) {
                        GroupConfig group = groupConfig.get(fieldBean.getField().getName());
                        getBasArrayField(fieldBean, t, rowdata, dataRowIndex, cloIndex, dataBean, group);
                        cloIndex += group.getLength();
                    } else if (fieldBean.getFieldType() == FieldType.ColumnGroup_ARRAY) {
                        GroupConfig group = groupConfig.get(fieldBean.getField().getName());
                        getColumnGroupField(fieldBean, t, rowdata, dataRowIndex, cloIndex, dataBean, group);
                        cloIndex += (group.getLength() * group.getGroupSize());
                    }
                }
                dataRowIndex++;
                hashVal += t.getHashVal();
            }
        }
        setHashVal(sheet, hashVal);
        log.debug("????????????");
        return this;
    }

    /**
     * ????????????????????????
     * @param fieldBean
     * @param excelVo
     * @param rowData
     * @param row
     * @param index
     * @param dataBean
     * @param group
     * @throws AdapterException
     * @throws ColumnErrorException
     */
    protected void getColumnGroupField(FieldBean fieldBean, BaseExcelVo excelVo, Row rowData, int row, int index, DataBean dataBean, GroupConfig group) throws AdapterException, ColumnErrorException {
        DataBean childDataBean = dataBean.getChildDataBean(fieldBean.getField().getName());
        List<BaseExcelVo> childVo = (List<BaseExcelVo>) dataBean.getFieldValue(fieldBean.getField().getName(), excelVo);
        if (childVo == null) {
            return;
        }
        int size = group.getFieldNames().size();
        if (ObjectHelper.isNotEmpty(childVo)) {
            for (int r = 0; r < childVo.size(); r++) {
                BaseExcelVo baseExcelVo = childVo.get(r);
                if (baseExcelVo != null) {
                    for (int i = 0; i < size; i++) {
                        FieldBean childFieldBean = childDataBean.getFiledBeanList().get(i);
                        if (childFieldBean.getFieldType() == FieldType.BASIC) {
                            getSimpleField(childFieldBean, baseExcelVo, rowData, row, index + r * size+i, childDataBean);
                        } else if (childFieldBean.getFieldType() == FieldType.BAS_ARRAY) {
                            GroupConfig childGroup = groupConfig.get(childFieldBean.getField().getName());
                            getBasArrayField(childFieldBean, baseExcelVo, rowData, row, index + r * size+i, childDataBean, childGroup);
                        } else if (childFieldBean.getFieldType() == FieldType.ColumnGroup_ARRAY) {
                            GroupConfig childGroup = groupConfig.get(childFieldBean.getField().getName());
                            getColumnGroupField(childFieldBean, baseExcelVo, rowData, row, index + r * size+i, childDataBean, childGroup);
                        }
                    }
                }
            }
        }
    }

    /**
     * ??????????????????????????????
     * @param fieldBean
     * @param excelVo
     * @param rowData
     * @param row
     * @param index
     * @param dataBean
     * @param group
     * @throws AdapterException
     * @throws ColumnErrorException
     */
    protected void getBasArrayField(FieldBean fieldBean, BaseExcelVo excelVo, Row rowData, int row, int index, DataBean dataBean, GroupConfig group) throws AdapterException, ColumnErrorException {
        Method outputMethod = fieldBean.getOutputMethod();
        outputMethod = outputMethod == null ? defaultAdapterMethod : outputMethod;
        String fieldName = fieldBean.getField().getName();
        List fieldValue = (List) dataBean.getFieldValue(fieldName, excelVo);
        if (ObjectHelper.isEmpty(fieldValue)) {
            return;
        }
        for (int i = 0; i < group.getLength(); i++) {
            try {
                excelVo.setCol(index + i);
                AdapterUtil.invokeOutputAdapterMethod(outputFactory, outputMethod, dataBean, fieldValue.get(i), fieldName, getCell(rowData, index + i));
            } catch (Exception e) {
                log.error("???????????????????????????????????????", e);
                if (e instanceof AdapterException) {
                    errorList.add(row, excelVo);
                    throw (AdapterException) e;
                } else {
                    throw new ColumnErrorException(row + 1, index + 1, Message.COLUMN_ERROR);
                }
            }
        }
    }

    /**
     * ???????????????????????????
     * @param fieldBean
     * @param excelVo
     * @param rowData
     * @param row
     * @param index
     * @param dataBean
     * @throws AdapterException
     * @throws ColumnErrorException
     */
    protected void getSimpleField(FieldBean fieldBean, BaseExcelVo excelVo, Row rowData, int row, int index, DataBean dataBean) throws AdapterException, ColumnErrorException {
        Method outputMethod = fieldBean.getOutputMethod();
        outputMethod = outputMethod == null ? defaultAdapterMethod : outputMethod;
        Object fieldValue = dataBean.getFieldValue(fieldBean.getField().getName(), excelVo);
        if (ObjectHelper.isNullOrEmptyString(fieldValue)) {
            return;
        }
        try {
            excelVo.setCol(index);
            AdapterUtil.invokeOutputAdapterMethod(outputFactory, outputMethod, dataBean, fieldValue, fieldBean.getField().getName(), getCell(rowData, index));
        } catch (Exception e) {
            log.error("ExportExcelService:getSimpleField???FieldName:{}??????", e, fieldBean.getField().getName());
            if (e instanceof AdapterException) {
                errorList.add(row, excelVo);
                throw (AdapterException) e;
            } else {
                throw new ColumnErrorException(row + 1, fieldBean.getField().getName(), Message.COLUMN_ERROR);
            }
        }
    }

    public ExportExcelService setGroupConfig(String fieldName, GroupConfig groupConfig) {
        this.groupConfig.put(fieldName, groupConfig);
        return this;
    }

    public List<BaseExcelVo> getErrorList() {
        return errorList;
    }

    public ExportExcelService setLanguage(ILanguage language) {
        this.language = language;
        return this;
    }

    public ExportExcelService finish() {
        outputFactory.finish();
        return this;
    }
}

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.ktm.mt.model.util.excel.adapter.IOAdapter;
import cn.com.ktm.mt.model.util.excel.annotation.ExcelColumnGroup;
import cn.com.ktm.mt.model.util.excel.annotation.ExcelVoConfig;
import cn.com.ktm.mt.model.util.excel.exception.AdapterException;
import cn.com.ktm.mt.model.util.excel.exception.ColumnErrorException;
import cn.com.ktm.mt.model.util.excel.exception.ExcelHeaderErrorException;
import cn.com.ktm.mt.model.util.excel.exception.ExcelVoErrorException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import cn.com.ktm.mt.model.util.excel.bean.BaseExcelVo;
import cn.com.ktm.mt.model.util.excel.bean.ColumnBean;
import cn.com.ktm.mt.model.util.excel.bean.DataBean;
import cn.com.ktm.mt.model.util.excel.bean.FieldBean;
import cn.com.ktm.mt.model.util.excel.bean.FieldType;
import cn.com.ktm.mt.model.util.excel.bean.GroupConfig;
import cn.com.ktm.mt.model.util.excel.json.JsonUtil;
import cn.com.ktm.mt.model.util.excel.utils.AdapterUtil;
import cn.com.ktm.mt.model.util.excel.utils.DicCodePool;
import cn.com.ktm.mt.model.util.excel.utils.Message;
import cn.com.ktm.mt.model.util.excel.utils.ObjectHelper;
import cn.com.ktm.mt.model.util.excel.utils.Reflections;

/**
 * ?????????
 * ??????????????????
 * ??????????????????????????????
 * ??????????????????
 * ?????????????????????????????????
 * ???????????????0???????????????????????????????????????????????????
 * User: Hyberbin
 * Date: 13-12-3
 * Time: ??????4:01
 * @param <T>
 */
public class ImportExcelService<T extends BaseExcelVo> extends BaseExcelService {
    protected final Class<T> voClass;
    protected final List<Field> fieldList = new ArrayList<Field>();
    protected final DicCodePool   dicCodePool;
    protected final Sheet         sheet;
    protected final DataBean      dataBean;
    protected final ExcelVoConfig config;
    protected final int           columnLenth;
    protected final IOAdapter inputFactory;
    protected final Method        defaultAdapterMethod;
    protected final List<T>                 errorList     = new ArrayList<T>();
    protected Map<String, GroupConfig>      groupConfig   = new HashMap<String, GroupConfig>();
    /**?????????????????????*/
    protected long                          hashVal       =0;
    /**????????????????????????*/
    protected boolean                       checked       =false;
    protected final Map<Integer,ColumnBean> columnBeanMap =new HashMap(0);

    /**
     * @param voClass
     * @param sheet
     * @throws Exception
     */
    public ImportExcelService(Class<T> voClass, Sheet sheet) throws Exception {
        if (voClass.isAnnotationPresent(ExcelVoConfig.class)) {
            config = voClass.getAnnotation(ExcelVoConfig.class);
        } else {
            throw new ExcelVoErrorException(voClass.getClass(), Message.EXCEL_VO_ERROR);
        }
        this.voClass = voClass;
        this.sheet = sheet;
        log.debug("???????????????????????????");
        readHead();
        log.debug("???????????????????????????????????????{}???", columnBeanMap.size());
        columnLenth = columnBeanMap.size();
        dataBean = new DataBean(fieldList, config.inputFactory(), config.outputFactory(), config.validateClass());
        Class factoryClass = config.inputFactory();
        log.debug("??????????????????????????????{}", factoryClass.getName());
        Constructor constructor = factoryClass.getConstructor(AdapterUtil.Constructor);
        dicCodePool = new DicCodePool();
        inputFactory = (IOAdapter) constructor.newInstance(dicCodePool);
        log.debug("????????????????????????");
        defaultAdapterMethod = AdapterUtil.getDefaultAdapterMethod(factoryClass);
        if (defaultAdapterMethod == null) {
            throw new IllegalArgumentException("?????????????????????????????????");
        }
        log.debug("????????????????????????{}", defaultAdapterMethod.getName());
        log.debug("????????????????????????????????????");
    }

    public ImportExcelService addDic(String name, List<Map> maps) {
        dicCodePool.addMap(name, maps);
        return this;
    }

    public ImportExcelService addDic(String name, Map<String, String> map) {
        dicCodePool.addMap(name, map);
        return this;
    }
    
    public ImportExcelService addDic(String name, String key,String value) {
        dicCodePool.addMap(name, key, value);
        return this;
    }

    /**
     * ????????????
     *
     * @throws ExcelHeaderErrorException
     */
    private void readHead() throws ExcelHeaderErrorException {
        int index = 0;
        try{//??????hash??????
            hashVal=getHashVal(sheet);
        }catch (Exception e){
             throw new ExcelHeaderErrorException(Message.FLAG_ERROR);
        }
        List<String> columns = new ArrayList<String>();
        Cell cell = getCell(sheet, HIDDEN_FIELD_HEAD, index);
        while (index == 0 || !isEmpty(cell)) {
            String field = getString(cell);
            ColumnBean columnBean= JsonUtil.toObject(field,ColumnBean.class);
            columns.add(field);
            if(columnBean.getLength()>0){//?????????????????????
                GroupConfig noNameGroup=groupConfig.get(columnBean.getColumnName());
                if(noNameGroup==null){
                    noNameGroup = GroupConfig.getNoNameGroup(columnBean.getSize(),columnBean.getLength());
                    groupConfig.put(columnBean.getColumnName(),noNameGroup);
                    Field accessibleField = Reflections.getAccessibleField(voClass, columnBean.getColumnName());
                    fieldList.add(accessibleField);
                }
                if(!ObjectHelper.isNullOrEmptyString(columnBean.getInnerColumn())){//??????????????????
                    noNameGroup.addField(columnBean.getInnerColumn());
                }else{
                    noNameGroup.addField(field);
                }
            }else {
                Field accessibleField = Reflections.getAccessibleField(voClass, columnBean.getColumnName());
                if(accessibleField==null){
                    log.error("{}????????????????????????{}",voClass.getSimpleName(),columnBean.getColumnName());
                }else{
                    fieldList.add(accessibleField);
                }
            }
            columnBeanMap.put(index,columnBean);
            index++;
            cell = getCell(sheet, HIDDEN_FIELD_HEAD, index);
        }
        if (ObjectHelper.isEmpty(columns)) {
            throw new ExcelHeaderErrorException(Message.HEAD_ERROR);
        }
    }

    /**
     * ????????????
     *
     * @param row ??????
     * @param excelVo
     * @param indexClo
     * @return ExcelVo??????
     * @throws ColumnErrorException
     * @throws AdapterException
     */
    public T getRow(int row, T excelVo, int indexClo) throws ColumnErrorException, AdapterException {
        Row sheetRow = getRow(sheet, row);
        if (!isEmpty(sheetRow, columnLenth)) {//????????????
            List<FieldBean> filedBeanList = dataBean.getFiledBeanList();
            int             cloIndex      =0;
            for (FieldBean fieldBean : filedBeanList) {
                if (fieldBean.getFieldType() == FieldType.BASIC) {
                    setSimpleField(fieldBean, excelVo, sheetRow, row, cloIndex, dataBean);
                    cloIndex++;
                } else if (fieldBean.getFieldType() == FieldType.BAS_ARRAY) {
                    GroupConfig groupConfig = this.groupConfig.get(fieldBean.getField().getName());
                    setBasArrayField(fieldBean, excelVo, sheetRow, row, cloIndex + indexClo, dataBean,groupConfig);
                    cloIndex+=groupConfig.getRealLength();
                } else if (fieldBean.getFieldType() == FieldType.ColumnGroup_ARRAY) {
                    GroupConfig groupConfig = this.groupConfig.get(fieldBean.getField().getName());
                    setColumnGroupField(fieldBean, excelVo, sheetRow, row, cloIndex + indexClo, dataBean,groupConfig);
                    cloIndex+=groupConfig.getRealLength()*groupConfig.getGroupSize();
                }
            }
            log.debug("????????????????????????"+JsonUtil.toJSON(excelVo));
            return excelVo;
        }
        return null;
    }

    /**
     * ???????????????????????????
     * @param fieldBean
     * @param excelVo
     * @param sheetRow
     * @param row
     * @param index
     * @param dataBean
     * @throws AdapterException
     * @throws ColumnErrorException
     */
    private void setColumnGroupField(FieldBean fieldBean, BaseExcelVo excelVo, Row sheetRow, int row, int index, DataBean dataBean,GroupConfig groupConfig) throws AdapterException, ColumnErrorException {
        ExcelColumnGroup annotation    = fieldBean.getField().getAnnotation(ExcelColumnGroup.class);
        Integer          length        = groupConfig.getRealLength();
        List             list          = new ArrayList();
        List<String>     fieldNames    = groupConfig.getFieldNames();
        DataBean         childDataBean = dataBean.getChildDataBean(fieldBean.getField().getName());
        int              childSize     =fieldNames.size();
        for (int i = 0; i < length; i++) {//????????????
            try {
                BaseExcelVo baseColumnGroup = (BaseExcelVo) annotation.type().newInstance();
                for (int j=0;j<fieldNames.size();j++) {
                    String field=fieldNames.get(j);
                    FieldBean childFieldBean=childDataBean.getFieldBean(field);
                    if (childFieldBean.getFieldType() == FieldType.BASIC) {
                        setSimpleField(childFieldBean, baseColumnGroup, sheetRow, row, index+i*childSize+j, childDataBean);
                    } else if (childFieldBean.getFieldType() == FieldType.BAS_ARRAY) {
                        GroupConfig childGroupConfig = this.groupConfig.get(childFieldBean.getField().getName());
                        setBasArrayField(childFieldBean, baseColumnGroup, sheetRow, row, index+i*childSize, childDataBean,childGroupConfig);
                    } else if (childFieldBean.getFieldType() == FieldType.ColumnGroup_ARRAY) {
                        GroupConfig childGroupConfig = this.groupConfig.get(childFieldBean.getField().getName());
                        setColumnGroupField(childFieldBean, baseColumnGroup, sheetRow, row,index+i*childSize, childDataBean,childGroupConfig);
                    }
                }
                list.add(baseColumnGroup);
            } catch (AdapterException e) {
               throw (AdapterException) e;
            }catch (Exception e) {
                throw new ColumnErrorException(row+1, index+1, Message.COLUMN_ERROR);
            }
        }
        dataBean.setFieldValue(fieldBean.getField().getName(), excelVo, list);
    }

    /**
     * ???????????????????????????
     * @param fieldBean
     * @param excelVo
     * @param sheetRow
     * @param row
     * @param index
     * @param dataBean
     * @throws AdapterException
     * @throws ColumnErrorException
     */
    private void setBasArrayField(FieldBean fieldBean, BaseExcelVo excelVo, Row sheetRow, int row, int index, DataBean dataBean,GroupConfig groupConfig) throws AdapterException, ColumnErrorException {
        ExcelColumnGroup annotation = fieldBean.getField().getAnnotation(ExcelColumnGroup.class);
        Integer length = groupConfig.getRealLength();
        Method inputMethod = fieldBean.getInputMethod();
        inputMethod = inputMethod == null ? defaultAdapterMethod : inputMethod;
        List list = new ArrayList();
        for (int i = 0; i < length; i++) {
            Cell cell=null;
            try {
                cell = getCell(sheetRow, i + index);
                excelVo.setCol(i + index);
                Object o = AdapterUtil.invokeInputAdapterMethod(inputFactory, inputMethod,dataBean, annotation.type(), fieldBean.getField().getName(), cell);
                list.add(o);
            } catch (Exception e) {
                setErrorStyle(cell);
                if (e instanceof AdapterException) {
                    throw (AdapterException) e;
                } else {
                    throw new ColumnErrorException(row+1,i+index+1, Message.COLUMN_ERROR);
                }
            }
        }
        dataBean.setFieldValue(fieldBean.getField().getName(), excelVo, list);
    }

    /**
     * ?????????????????????????????????
     * @param fieldBean
     * @param excelVo
     * @param sheetRow
     * @param row
     * @param index
     * @param dataBean
     * @throws AdapterException
     * @throws ColumnErrorException
     */
    private void setSimpleField(FieldBean fieldBean, BaseExcelVo excelVo, Row sheetRow, int row, int index, DataBean dataBean) throws AdapterException, ColumnErrorException {
        Cell cell = getCell(sheetRow, index);
        try {
            ColumnBean columnBean = columnBeanMap.get(index);
            if(!ObjectHelper.isNullOrEmptyString(columnBean.getTookValue())){
                dataBean.setTargetFieldValue(columnBean.getTookName(), excelVo, columnBean.getTookValue());
            }
            Method inputMethod = fieldBean.getInputMethod();
            inputMethod = inputMethod == null ? defaultAdapterMethod : inputMethod;
            excelVo.setCol(index);
            String name = fieldBean.getField().getName();
            excelVo.setCell(name,cell);
            Object o = AdapterUtil.invokeInputAdapterMethod(inputFactory, inputMethod,dataBean, fieldBean.getField().getType(), name, cell);
            dataBean.setFieldValue(name, excelVo, o);
            log.debug("geted field value:row:{},index:{},name:{},value:{}",row,index,name,o);
        } catch (Exception e) {
            setErrorStyle(cell);
            if (e instanceof AdapterException) {
                throw (AdapterException) e;
            } else {
                throw new ColumnErrorException(row+1, fieldBean.getField().getName(), Message.COLUMN_ERROR);
            }
        }
    }

    /**
     * ??????
     *
     * @return
     * @throws ExcelVoErrorException
     */
    public List<T> doImport() throws ExcelVoErrorException {
        List list = new ArrayList();
        long hasCode=0;
        for (int i = START_ROW; i <= sheet.getLastRowNum(); i++) {
            T t = null;
            try {
                t = voClass.newInstance();
                t.setRow(i);
                T row = getRow(i, t, 0);
                if(row!=null){
                    if(!row.validate()) {
                        errorList.add(row);
                        continue;
                    }
                }else{
                    continue;//????????????????????????????????????
                }
                list.add(row);
            } catch (ColumnErrorException e) {//Excel???????????????
                log.error(e.getMessage(), e);
                t.setMessage(e.getMessage());
                errorList.add(t);
            } catch (AdapterException e) {//??????????????????
                log.error(e.getMessage(), e);
                t.setMessage(e.getMessage());
                errorList.add(t);
            } catch (Exception e) {//??????????????????
                ExcelVoErrorException errorException = new ExcelVoErrorException(voClass, Message.VO_INSTANCE_ERROR);
                log.error(errorException.getMessage(), e);
                throw errorException;
            }
            hasCode+=t.getHashVal();
        }
        checked=hasCode==hashVal;
        return list;
    }

    public List<T> getErrorList() {
        return errorList;
    }

    public ImportExcelService finish() {
        inputFactory.finish();
        return this;
    }

    public Map<Integer, ColumnBean> getColumnBeanMap() {
        return columnBeanMap;
    }

    /**
     * ????????????????????????????????????
     * @return
     */
    public boolean isChecked() {
        return checked;
    }
}

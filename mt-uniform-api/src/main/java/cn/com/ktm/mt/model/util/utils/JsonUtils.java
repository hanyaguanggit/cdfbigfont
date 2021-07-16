package cn.com.ktm.mt.model.util.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * json转换工具类
 *
 * @author July july_sky@foxmail.com
 * @version 1.0
 * @date 2015年4月10日下午2:46:37
 * @Copyright ©2003-2015 北京春秋永乐文化传播有限公司. All Rights Reserved.
 */
public class JsonUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 将Object对象转换成json字符串
     *
     * @param obj
     * @return
     * @throws Exception
     * @author July july_sky@foxmail.com
     * @date 2015年4月10日下午2:46:44
     */
    public static String format(Object obj) throws Exception {
        if (StringUtils.isBlank(obj)) {
            return null;
        }
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        try (StringWriter sw = new StringWriter(); JsonGenerator gen = new JsonFactory().createGenerator(sw);) {
            mapper.writeValue(gen, obj);
            return sw.toString();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 将json字符串转换成Map
     *
     * @param json
     * @return
     * @throws Exception
     * @author July july_sky@foxmail.com
     * @date 2015年4月10日下午2:46:56
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseMap(String json) throws Exception {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return (Map<String, Object>) mapper.readValue(json, Map.class);
    }

    /**
     * 将json字符串转换成List
     *
     * @param json
     * @return
     * @throws Exception
     * @author July july_sky@foxmail.com
     * @date 2015年4月10日下午2:47:09
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> parseList(String json) throws Exception {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return (List<Map<String, Object>>) mapper.readValue(json, Map.class);
    }

    public static Object parseObject(String json) throws Exception {
        if (ValidUtil.isNullStr(json)) {
            return null;
        }
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper.readValue(json, Object.class);
    }

    /**
     * json字符串转list<class>
     * gaocl
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> parseListClass(String json, Class<T> clazz) throws Exception {

        if("[]".equals(json) || "".equals(json) || null == json){
            return null;
        }
        List lst = JSONObject.parseObject(json, ArrayList.class);
        if (ValidUtil.isEmptyList(lst)) {
            return null;
        }
        List<T> list = new ArrayList<>();
        for (Object obj : lst) {
            list.add(JSONObject.parseObject(String.valueOf(obj), clazz));
        }
        return list;
    }


    /**
     * json转对象
     * wangjianqiang
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Object> T fromString(String json, Class<T> clazz) {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            return om.readValue(json, clazz);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return null;
    }


    public static String tojson(Object o) {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 创建一个json数据，格式如下
     * {
     * status: "success",
     * message: "add a new user"
     * }
     *
     * @param status  状态信息
     * @param message 提示信息
     */
    public static String createJson(String status, String message) {
        JSONObject json = new JSONObject();

        json.put("status", status);
        json.put("message", message);

        return json.toString();
    }


}

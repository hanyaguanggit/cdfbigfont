package cn.com.ktm.mt.model.util.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

 /**
  * 
  * @author ygl
  *
  */
public class JsonUtil {
    private static ObjectMapper mapper = getMapper();

    private static ObjectMapper getMapper() { 
        ObjectMapper om = new ObjectMapper();

        /* 该属性影响JSON格式化时，是否忽略未知属性。不可修改 */
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        String rst = null;
        try {
            rst = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            rst = null;
        }
        return rst;
    }

    public static <T> T fromString(String json, Class<T> cls) {
        if (json == null || json.trim().isEmpty() || cls == null) {
            return null;
        }
        T obj = null;
        try {
            obj = mapper.readValue(json, cls);
        } catch (IOException e) {
            e.printStackTrace();
            obj = null;
        }
        return obj;
    }

    private static <T> T fromString(String json, JavaType type) {
        if (json == null || json.trim().isEmpty() || type == null) {
            return null;
        }
        T obj = null;
        try {
            obj = mapper.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace();
            obj = null;
        }
        return obj;
    }

    public static <T> List<T> fromListString(String json, Class<T> cls) {
        return JSON.parseArray(json, cls);
//        return fromString(json, getCollectionType(Map.class, cls));
    }

    public static <K, V> Map<K, V> fromMapString(String json, Class<K> keyClass, Class<V> valueClass) {
        return fromString(json, getCollectionType(Map.class, keyClass, valueClass));
    }

    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
    public static void main(String[] args) throws ParseException {


    }
}

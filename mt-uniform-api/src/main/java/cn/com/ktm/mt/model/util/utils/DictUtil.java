package cn.com.ktm.mt.model.util.utils;

import java.util.HashMap;
import java.util.Map;

public class DictUtil {

    private static Map<String, Map<String, String>> dictData = new HashMap<String, Map<String, String>>();

    public static Map<String, String> getDictData(String type) {
        return dictData.get(type);
    }

    public static void putDictData(String type, Map<String, String> data) {
        dictData.put(type, data);
    }

}

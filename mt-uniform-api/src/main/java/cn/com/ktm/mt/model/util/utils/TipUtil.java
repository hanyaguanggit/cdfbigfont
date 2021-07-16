package cn.com.ktm.mt.model.util.utils;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

/**
 * 获取tip.properties的提示信息 Created by gaocl on 16-3-14.
 */
public class TipUtil {
    private static final ResourceBundle resource = ResourceBundle.getBundle("tip");

    public static String get(String key) {
        String str = "";
        try {
            str = new String(resource.getString(key).getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String get(String key, Object... params) {
        String str = "";
        try {
            str = new String(resource.getString(key).getBytes("ISO-8859-1"), "UTF-8");
            return String.format(str, params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

}

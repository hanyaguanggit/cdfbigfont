package cn.com.ktm.mt.model.util.utils;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

/**
 * 获取expire.properties的提示信息
 */
public class ExpireUtil {
	private static final ResourceBundle resource = ResourceBundle.getBundle("expire");

	public static Integer get(String key) {
		String str = "";
		try {
			str = new String(resource.getString(key).getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return Integer.valueOf(str);
	}

}

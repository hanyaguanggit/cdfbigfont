package cn.com.ktm.mt.model.util.utils.http;


 

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.ktm.mt.model.util.utils.Constant;
import cn.com.ktm.mt.model.util.utils.ValidUtil;

/**
 * cookie工具类
 * IE和Chrome内核的浏览器会认为http://localhost为无效的域名 不保存cookie，需在火狐上测试本地， 或改为127.0.0.1
 * Created by gaocl on 16-3-2.
 */
public class CookieUtil {

    public static final int COOKIE_MAXAGE = 20 * 60;

    public static void addCookie(HttpServletResponse response, String name, String value) {

        try {
            //当cookie中包含有等号、空格、分号等特殊字符时，可能会导致数据丢失、或者不能解析的错误, 需编码
            // js decodeURIComponent() 解码
            if (!ValidUtil.isNullStr(value)) {

                value = URLEncoder.encode(value, Constant.ENCODE);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(COOKIE_MAXAGE);
        response.addCookie(cookie);

    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }

    public static void updCookie(HttpServletRequest request, HttpServletResponse response, String name, String newValue) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    cookie.setValue(newValue);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    public static void updCookie(HttpServletRequest request, HttpServletResponse response, String name, String newValue, int maxAge) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    cookie.setValue(newValue);
                    cookie.setPath("/");
                    if (maxAge > 0) cookie.setMaxAge(maxAge);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    public static void updCookieMaxAge(HttpServletRequest request, HttpServletResponse response, String name, int maxAge) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                	cookie.setPath("/");
                    if (maxAge > 0) cookie.setMaxAge(maxAge);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    public static String getCookieByName(HttpServletRequest request, String name) {
        if (name == null) return null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
                
            }
        }
        return null;
    }

    private static Map<String, Cookie> getCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
    
    


}

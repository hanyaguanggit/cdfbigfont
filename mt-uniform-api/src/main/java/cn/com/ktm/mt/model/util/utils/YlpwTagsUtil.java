package cn.com.ktm.mt.model.util.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

/**
 * Created by gaocl on 16-3-24.
 */
public class YlpwTagsUtil {

    public static String toEncode(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(URLEncoder.encode(str, "UTF-8"), "UTF-8");
    }

    public static String toDecode(String str) throws UnsupportedEncodingException {
        return URLDecoder.decode(URLDecoder.decode(str, "UTF-8"), "UTF-8");
    }

    public static String timestampFormat(String time, String format) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.parseLong(time)));
    }

    /*将小时毫秒数转换成小时分钟数  */
    public static String timesFormat(String time, String format) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        if (StringUtils.isEmpty(format)) {
            format = "HH:mm";
        }
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.set(2010, 0, 1); //2010年1月1日开始  月需要减一
        fromCalendar.set(Calendar.HOUR_OF_DAY, (Integer.parseInt(time) / 3600000));
        fromCalendar.set(Calendar.MINUTE, (Integer.parseInt(time) % 3600000) / 60000);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(fromCalendar.getTimeInMillis()));
    }

    public static String dateFormat(String date, String format) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd";
        }

        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.set(2010, 0, 1); //2010年1月1日开始  月需要减一


        fromCalendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(date));

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(fromCalendar.getTimeInMillis()));
    }

    /*去掉日期中含有0的部分 月份或者date*/
    public static String dateSubZero(String date, String dateMonthTag) {
        String retStr = "";
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        if (dateMonthTag.trim().equals("1")) {
            if (date.substring(5, 7).startsWith("0")) {
                retStr = date.substring(6, 7);
            } else {
                retStr = date.substring(5, 7);
            }
        } else if (dateMonthTag.trim().equals("2")) {
            if (date.substring(8, 10).startsWith("0")) {
                retStr = date.substring(9, 10);
            } else {
                retStr = date.substring(8, 10);
            }

        }else if (dateMonthTag.trim().equals("3")) {
                retStr = date.substring(5, 10);
             }
        return retStr;
    }
}

package cn.com.ktm.mt.model.util.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static Calendar initCalendar() {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.set(2010, 0, 1); //2010年1月1日开始  月需要减一 从0开始到11.
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        return fromCalendar;
    }

    // 日期转换为 long
    public static int dateToInt(Date endDate) {
        Calendar init = initCalendar();
        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);
        Long i = (toCalendar.getTimeInMillis() - init.getTimeInMillis()) / (1000 * 60 * 60 * 24);
        return i.intValue();
    }


    // 日期int 转换为时间
    public static Date intToDate(int date) {
        Calendar fromCalendar = initCalendar();
        fromCalendar.add(Calendar.DAY_OF_MONTH, date);
        return fromCalendar.getTime();
    }

    //毫秒转换为 hh:ss格式
    public static String longToTime(int ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        long hour = ms / hh;
        long minute = (ms - hour * hh) / mi;
        // long second = (ms - hour * hh - minute * mi) / ss;
        // long milliSecond = ms - hour * hh - minute * mi - second * ss;
        String strHour = hour < 10 ? "0" + hour : "" + hour;// 小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;// 分钟
        // strSecond = second < 10 ? "0" + second : "" + second;// 秒
        // String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" +
        // milliSecond;// 毫秒
        // strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" +
        // strMilliSecond;
        return strHour + ":" + strMinute;
    }

    /**
     * 格式 hh:mm
     * 转换为毫秒
     *
     * @param time
     * @return
     */
    public static int timeToInt(String time) {
        String[] split = time.split(":");
        return ((Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1])) * 60) * 1000;
    }

    public static void main(String[] args) {
        //System.out.println(DateUtils.dateToLong(new Date()));
        try {
            System.out.println(DateUtils.dateToInt(DateUtil.parse("2019-09-02", "yyyy-MM-dd")));
        } catch (ParseException pse) {

        }
    }
}

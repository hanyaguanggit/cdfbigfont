package cn.com.ktm.mt.model.util.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateUtil {
    public static final String beginDateStr = "2010-01-01 00:00:00";
    public static final String beginTimeStr = "00:00:00";
    public static final String endTimeStr = "23:59:59 999";
    public static final String timeSeparator = ":";
    public static final String format1 = "yyyy-MM-dd HH:mm:ss";
    public static final String format2 = "yyyy年MM月dd日 HH:mm:ss";
    public static final String format3 = "yyyy-MM-dd";
    public static final String format4 = "yyyy/MM/dd";
    public static final String format5 = "yyyyMMdd";
    public static final String format6 = "HH:mm:ss";
    public static final String format7 = "HH:mm";

    public static final String[] chineseWeeks = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};

    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();
    private static ThreadLocal<SimpleDateFormat> threadLocal2 = new ThreadLocal<SimpleDateFormat>();
    private static ThreadLocal<SimpleDateFormat> threadLocal3 = new ThreadLocal<SimpleDateFormat>();

    public static SimpleDateFormat sdfFormat() {
        SimpleDateFormat df = threadLocal.get();
        if (df == null) {
            df = new SimpleDateFormat(format1);
            threadLocal.set(df);
        }
        return df;
    }

    public static SimpleDateFormat sdfFormatHHMMSS() {
        SimpleDateFormat df = threadLocal2.get();
        if (df == null) {
            df = new SimpleDateFormat(format6);
            threadLocal2.set(df);
        }
        return df;
    }

    public static SimpleDateFormat sdfFormatHHMM() {
        SimpleDateFormat df = threadLocal3.get();
        if (df == null) {
            df = new SimpleDateFormat(format7);
            threadLocal3.set(df);
        }
        return df;
    }

    public static synchronized SimpleDateFormat sdfParamFormat(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df;
    }

    /**
     * 时间格式转换
     *
     * @param formatDate
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parse(String formatDate, String pattern) throws ParseException {
        if (ValidUtil.isNullStr(formatDate)) {
            return null;
        }
        if (ValidUtil.isNullStr(pattern)) {
            pattern = format1;
        }
        return sdfParamFormat(pattern).parse(formatDate);
    }

    /**
     * 获取指定格式的时间format
     *
     * @param formatStr
     * @param date
     * @return
     */
    public static String getDateFormatFromDate(String formatStr, Date date) {
        if (null == date) {
            return "";
        }
        return sdfParamFormat(formatStr).format(date);
    }

    /**
     * 获取当前日期时间（毫秒）
     *
     * @return
     */
    public static long getCurDateTime() {
        return System.currentTimeMillis();
    }

    public static int getCurYear() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR);
    }

    /**
     * 获取指定日期的截止时间
     *
     * @param date
     * @return
     */
    public static long getDateTimeEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
    }

    /**
     * 获取指定日期的起始时间
     *
     * @param date
     * @return
     */
    public static long getDateTimeBegin(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取几个月后的日期时间
     *
     * @param n    可负数
     * @param date
     * @return
     */
    public static Date getDateTimeAddMonth(int n, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, n);
        return calendar.getTime();
    }

    /**
     * 获取几个月后的最后一天
     *
     * @param n    可负数
     * @param date
     * @return
     */
    public static Date getMaxDateTimeAddMonth(int n, Date date) {
        Date date1 = getDateTimeAddMonth(n, date);
        Calendar calendar = Calendar.getInstance();
        /**
         * 实例化日历各个字段,这里的day为实例化使用
         */
        calendar.setTime(date1);
        /**
         * Calendar.Date:表示一个月中的某天
         * calendar.getActualMaximum(int field):返回指定日历字段可能拥有的最大值
         */
        int maxDay = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), maxDay, 0, 0, 0);
        return calendar.getTime();
    }

    /**
     * 获取某个日期的当月第一天
     *
     * @param n    可负数
     * @param date
     * @return
     */
    public static Date getMinDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonth(), 1);
        return calendar.getTime();
    }

    /**
     * 根据年月，生成月份开始日期。
     *
     * @param n    可负数
     * @param date
     * @return
     */
    public static Date getInitMonthDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getTime();
    }

    /**
     * 根据年月，生成月份结束日期。
     *
     * @param n    可负数
     * @param date
     * @return
     */
    public static Date getMaxMonthDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    /**
     * 获取某个日期距离n天的日期
     *
     * @param n    可以为负数
     * @param date 某个日期
     * @return
     */
    public static Date getDateTimeAddDay(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, n);
        return calendar.getTime();
    }

    /**
     * 获取某个日期距离n天的日期（yyyy-MM-dd）
     *
     * @param n    可以为负数
     * @param date 某个日期
     * @return
     */
    public static String getDateAddDay(Date date, int n) {
        Date d = getDateTimeAddDay(date, n);
        return getDateFormatFromDate(format3, d);
    }

    /**
     * 获取某个日期距离n年的日期（Date）
     *
     * @param n    可以为负数
     * @param date 某个日期
     * @return
     */
    public static Date getDateTimeAddYear(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, n);
        return calendar.getTime();
    }

    /**
     * 获取某个日期距离n年的日期（yyyy-MM-dd）
     *
     * @param n    可以为负数
     * @param date 某个日期
     * @return
     */
    public static String getDateAddYear(Date date, int n) {
        Date d = getDateTimeAddYear(date, n);
        return getDateFormatFromDate(format3, d);
    }


    /**
     * 获取当前天数
     * 起始时间为2010.1.1
     *
     * @return
     */
    public static int getCurDays() {
        Date beginDate = null;
        try {
            beginDate = sdfFormat().parse(beginDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = getCurDateTime() - beginDate.getTime();
        return (int) (time / (24 * 60 * 60 * 1000));
    }

    /**
     * 天数转日期
     *
     * @param days
     * @return
     * @throws ParseException
     */
    public static Date getDateFromDays(int days) {
        Date beginDate = null;
        try {
            beginDate = sdfFormat().parse(beginDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     * 天数转日期Format
     *
     * @param days
     * @return
     * @throws ParseException
     */
    public static String getDateFormatFromDays(int days, String formatStr) {
        Date beginDate = null;
        try {
            beginDate = sdfFormat().parse(beginDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return sdfParamFormat(formatStr).format(cal.getTime());
    }

    /**
     * 获取指定格式的时间format
     *
     * @return
     */
    public static String getDateFormatFromInt(long dateInt) {
        if (dateInt == 0l)
            return "";
        return sdfFormat().format(dateInt);
    }

    /**
     * 获取指定格式的时间format
     *
     * @param dateInt
     * @param dateFormat
     * @return
     */
    public static String getDateFormatFromInt(long dateInt, String dateFormat) {
        if (dateInt == 0l)
            return "";
        return sdfParamFormat(dateFormat).format(dateInt);
    }

    /**
     * 天数和时间转日期
     *
     * @param days
     * @return
     */
    public static Date getDateFromDaysAndTime(int days, int time) {
        Date beginDate = null;
        try {
            beginDate = sdfFormat().parse(beginDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        cal.add(Calendar.DAY_OF_MONTH, days);
        cal.add(Calendar.HOUR_OF_DAY, time / (60 * 60 * 1000));
        cal.add(Calendar.MINUTE, (time / (60 * 1000)) % 60);
        cal.add(Calendar.SECOND, (time / 1000) % 60);
        cal.add(Calendar.MILLISECOND, time % 1000);
        return cal.getTime();
    }

    /**
     * 天数和时间转日期Format
     *
     * @param days
     * @param time
     * @param formatStr
     * @return
     */
    public static String getDateFormatFromDaysAndTime(int days, int time, String formatStr) {
        Date beginDate = null;
        try {
            beginDate = sdfFormat().parse(beginDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        cal.add(Calendar.DAY_OF_MONTH, days);
        cal.add(Calendar.HOUR_OF_DAY, time / (60 * 60 * 1000));
        cal.add(Calendar.MINUTE, (time / (60 * 1000)) % 60);
        cal.add(Calendar.SECOND, (time / 1000) % 60);
        cal.add(Calendar.MILLISECOND, time % 1000);
        return sdfParamFormat(formatStr).format(cal.getTime());
    }

    /**
     * 获取指定时间的天数
     *
     * @param date
     * @return
     */
    public static int getDaysFromDate(Date date) {
        Date beginDate = null;
        try {
            beginDate = sdfFormat().parse(beginDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = date.getTime() - beginDate.getTime();
        return (int) (time / (24 * 60 * 60 * 1000));
    }

    /**
     * 获取指定时间Format的天数
     *
     * @param dateFormat
     * @return
     */
    public static int getDaysFromDateFormat(String dateFormat) {
        Date beginDate = null;
        long time = 0;
        try {
            beginDate = sdfFormat().parse(beginDateStr);

            time = sdfFormat().parse(dateFormat).getTime() - beginDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) (time / (24 * 60 * 60 * 1000));
    }

    public static int getDaysFromDateFormat(String dateFormat, String format) {
        SimpleDateFormat simpleDateFormat = sdfParamFormat(format);
        Date beginDate = null;
        long time = 0;
        try {
            beginDate = simpleDateFormat.parse(beginDateStr);

            time = simpleDateFormat.parse(dateFormat).getTime() - beginDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) (time / (24 * 60 * 60 * 1000));
    }

    /**
     * 获取指定时间的时间值
     *
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static int getTimeFromTime(int hour, int minute, int second) {
        return hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
    }


    /**
     * 获取指定时间Format的时间值
     *
     * @param timeFormat
     * @return
     */
    public static int getTimeFromTimeFormatHM(String timeFormat) {
        String[] time = timeFormat.split(timeSeparator);
        return getTimeFromTime(Integer.parseInt(time[0]), Integer.parseInt(time[1]), 0);
    }

    /**
     * 获取指定时间Format的时间值
     *
     * @param timeFormat
     * @return
     */
    public static int getTimeFromTimeFormatHMS(String timeFormat) {
        String[] time = timeFormat.split(timeSeparator);
        return getTimeFromTime(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
    }

    /**
     * 获取每日起始时间值
     *
     * @return
     */
    public static int getTimeBegin() {
        return 0;
    }

    /**
     * 获取每日截止时间值
     *
     * @return
     */
    public static int getTimeEnd() {
        return getTimeFromTime(23, 59, 59) + 999;
    }

    /**
     * 获取指定时间值的时间Format(HH:MM:SS)
     *
     * @param time
     * @return
     */
    public static String getTimeFormatFromTime(int time) {
        return sdfFormatHHMMSS().format(time - 8 * 60 * 60 * 1000);
    }


    /**
     * 获取指定时间值的时间Format(HH:MM)
     *
     * @param time
     * @return
     */
    public static String getTimeFormatFromTimeHM(int time) {
        return sdfFormatHHMM().format(time - 8 * 60 * 60 * 1000);
    }

    /**
     * 获取两个日期间的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDaysBetween(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * 获取指定日期所在周的星期一的日期
     *
     * @param dt
     * @return
     */
    public static Date getBeginWeekOfDate(Date dt) {
        // String[] weekDay2s = {"星期一", "星期二","星期三", "星期四", "星期五", "星期六","星期日"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        if (w == 0)
            w = 6;
        else
            w = w - 1;

        //算出差额，然后日期减去所差
        return getDateTimeAddDay(dt, -w);
    }

    /**
     * 获取指定日期的星期几
     *
     * @param dt
     * @return
     */
    public static int getWeekOfDate(Date dt) {
        // String[] weekDay2s = {"星期一", "星期二","星期三", "星期四", "星期五", "星期六","星期日"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        if (w == 0)
            w = 6;
        else
            w = w - 1;

        return w;
    }

    /**
     * 获取指定日期的星期几
     *
     * @param dt
     * @return
     */
    public static int getDateInWeek(Date dt) {
        // String[] weekDay2s = {"星期一", "星期二","星期三", "星期四", "星期五", "星期六","星期日"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }

    /**
     * 指定天数是否是工作日
     *
     * @param inTime
     * @return
     */
    public static boolean isWeekDay(Integer inTime) {
        Date d = getDateFromDays(inTime);
        int week = getWeekOfDate(d);
        if (week <= 4) {
            return true;
        }
        return false;
    }

    /**
     * 指定天数是否是双休日
     *
     * @param inTime
     * @return
     */
    public static boolean isRestDay(Integer inTime) {
        Date d = getDateFromDays(inTime);
        int week = getWeekOfDate(d);
        if (week == 5 || week == 6) {
            return true;
        }
        return false;
    }

    /**
     * 指定天数是否是工作日
     *
     * @param inTime
     * @return
     */
    public static boolean isWeekDate(String inTime) {
        SimpleDateFormat sdf = sdfParamFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(inTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int week = getWeekOfDate(date);
        if (week <= 4) {
            return true;
        }
        return false;
    }

    /**
     * 指定天数是否是双休日
     *
     * @param inTime
     * @return
     */
    public static boolean isRestDate(String inTime) {
        SimpleDateFormat sdf = sdfParamFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(inTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int week = getWeekOfDate(date);
        if (week == 5 || week == 6) {
            return true;
        }
        return false;
    }


    public static int getGapWeekOfDate(Date dt) {
        // String[] weekDay2s = {"星期一", "星期二","星期三", "星期四", "星期五", "星期六","星期日"};

        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        if (w == 0)
            w = 6;
        else
            w = w - 1;

    /* 算出差额，然后日期减去所差 */
        return w;
    }

    public static boolean compareDateSize(Date before, Date after) {
        if (before == null || after == null) {
            return false;
        }
        long dif = after.getTime() - before.getTime();
        if (dif > 0l)
            return true;
        return false;
    }

    /**
     * 将时间戳形式的时间数据，转化成可读的日期格式
     *
     * @param time
     * @param format
     * @return
     */
    public static String timestampFormat(String time, String format) {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = sdfParamFormat(format);
        return sdf.format(new Date(Long.parseLong(time)));
    }


    /**
     * 獲取指定日期所在月的第一天
     *
     * @param date
     * @return
     */
    public static Long getMonthFirstDay(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, c.getActualMinimum(Calendar.DATE));
        System.out.println(c.getTime());
        return DateUtil.getDateTimeBegin(c.getTime());
    }


    /**
     * 獲取指定日期所在月的第一天
     *
     * @param date
     * @return
     */
    public static Long getMonthEndDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));

        return DateUtil.getDateTimeEnd(c.getTime());
    }

    /**
     * 获取某年某月的第一天
     */
    public static String getLastDayOfMonth(int year, int month) {
        month = month - 1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return sdfParamFormat("yyyy-MM-dd ").format(cal.getTime());
    }

    /**
     * 获取某年某月的最后一天
     */
    public static String getFirstDayOfMonth(int year, int month) {
        month = month - 1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return sdfParamFormat("yyyy-MM-dd ").format(cal.getTime());
    }

    /**
     * 天数和时间转换为秒时间戳
     *
     * @param days
     * @return
     */
    public static long getTimeStampFromDaysAndTime(int days, int time) {
        Date beginDate = null;
        try {
            beginDate = sdfFormat().parse(beginDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        cal.add(Calendar.DAY_OF_MONTH, days);
        cal.add(Calendar.HOUR_OF_DAY, time / (60 * 60 * 1000));
        cal.add(Calendar.MINUTE, (time / (60 * 1000)) % 60);
        cal.add(Calendar.SECOND, (time / 1000) % 60);
        cal.add(Calendar.MILLISECOND, time % 1000);
        return cal.getTime().getTime() / 1000;
    }

    public static int getAge(String IDCardNum) {
        Calendar cal1 = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        System.out.println(IDCardNum.substring(12, 14));
        cal1.set(Integer.parseInt(IDCardNum.substring(6, 10)), Integer.parseInt(IDCardNum.substring(10, 12)), Integer.parseInt(IDCardNum.substring(12, 14)));
        return getYearDiff(today, cal1);
    }

    public static int getYearDiff(Calendar cal, Calendar cal1) {
        int m = (cal.get(cal.MONTH)) - (cal1.get(cal1.MONTH));
        int y = (cal.get(cal.YEAR)) - (cal1.get(cal1.YEAR));
        return (y * 12 + m) / 12;
    }

    /**
     * 获取指定日期前n天的日期
     *
     * @param specifiedDay yyyy-MM-dd
     * @return yyyy-MM-dd
     */
    public static String getSpecifiedDayBefore(String specifiedDay, int i) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = sdfParamFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - i);

        String dayBefore = sdfParamFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    /**
     * 获取指定日期的截止时间 秒
     *
     * @param date
     * @return
     */
    public static long getTimeStampeDateEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis() / 1000;
    }

    /**
     * 根据天数获取日期时间
     *
     * @param days
     * @return
     */
    public static long getDateTimeFromDays(int days) {
        return getDateFromDays(days).getTime();
    }

}

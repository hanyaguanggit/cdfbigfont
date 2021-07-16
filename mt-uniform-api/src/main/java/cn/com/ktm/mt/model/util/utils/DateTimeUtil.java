package cn.com.ktm.mt.model.util.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class DateTimeUtil {

    private static Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

	public static final String beginDateStr = "2010-01-01 00:00:00";
	public static final String beginTimeStr = "00:00:00";
	public static final String endTimeStr = "23:59:59 999";
	public static final String timeSeparator = ":";
	public static final SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat sdfFormatHHMMSS = new SimpleDateFormat("HH:mm:ss");
	public static final SimpleDateFormat sdfFormatHHMM = new SimpleDateFormat("HH:mm");
	public static final String format1 = "yyyy-MM-dd HH:mm:ss";
	public static final String format2 = "yyyy年MM月dd日 HH:mm:ss";
	public static final String format3 = "yyyy-MM-dd";
	public static final String format4 = "yyyy/MM/dd";
	public static final String format5 = "yyyyMMdd";
	public static final String[] chineseWeeks = {"星期一", "星期二","星期三", "星期四", "星期五", "星期六","星期日"};

	private static ThreadLocal<Long> CURRENT_TIME = new ThreadLocal<>();



	/**
	 * 将当前时间保存到 ThreadLocal 中
	 */
	public static void initThreadLocalTime() {
	    CURRENT_TIME.set(getCurTime());
        logger.debug("initThreadLocalTime() is OK, time={}", getCurTime(true));
	}

	/**
	 * 清空 ThreadLocal 中保存的时间
	 */
	public static void removeThreadLocalTime() {
	    CURRENT_TIME.remove();
	}

	/**
	 * 获取当前时间（毫秒）
	 * @return
	 */
	public static long getCurTime() {
	    return getCurTime(false);
	}

	/**
	 * 获取下一年当前时间（毫秒）= 当前时间 + 1年
	 *
	 * @return long
	 */
	public static long getNextYearTime() {

		return getNextYearTime(getCurTime(false));
	}

	/**
	 * 获取上一年当前时间（毫秒）= 当前时间 - 1年
	 *
	 * @return long
	 */
	public static long getLastYearTime() {

		return getLastYearTime(getCurTime(false));
	}

	/**
	 * 根据传入的时间戳获取上一年当前时间（毫秒）= 当前时间 - 1年
	 *
	 * @param time
	 *         时间戳
	 *
	 * @return long
	 */
	public static long getLastYearTime(Long time) {

		return getYearTime(time, -1);
	}

	/**
	 * 根据传入的时间戳获取下一年当前时间（毫秒）= 当前时间 + 1年
	 *
	 * @param time
	 *         时间戳
	 *
	 * @return long
	 */
	public static long getNextYearTime(Long time) {

		return getYearTime(time,1);
	}

	/**
	 * 根据传入的时间戳 计算year+amount
	 *
	 * @param time
	 *         时间戳
	 * @param amount
	 *         年份+amount,可以为负数
	 *
	 * @return long
	 */
	private static long getYearTime(Long time, Integer amount) {

		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(new Date(time));
		gregorianCalendar.add(Calendar.YEAR, amount);

		return gregorianCalendar.getTimeInMillis();
	}
	/**
     * 获取当前时间（毫秒）
     *
	 * @param isThreadLocal 是否使用 ThreadLocal 中保存的时间
	 * @return
	 */
	public static long getCurTime(boolean isThreadLocal) {

	    if (isThreadLocal) {
	        Long currentTime = CURRENT_TIME.get();
	        if (currentTime != null && currentTime > 0) {
	            return currentTime;
	        }
	    }

	    return System.currentTimeMillis();
	}

    /**
     * 获取今天的日期（毫秒）（00:00:00）
     *
     * @return
     */
    public static long getCurDate() {
        return getCurDate(false);
    }

    /**
     * 获取今天的日期（毫秒）（00:00:00）
     *
     * @param isThreadLocal 是否使用 ThreadLocal 中保存的时间
     * @return
     */
    public static long getCurDate(boolean isThreadLocal) {
        return getFirstTimeForDate(getCurTime(isThreadLocal));
    }

    /**
     * 计算该时间对应的日期的开始时间（毫秒）（00:00:00）
     *
     * @param time 任意时间
     * @return
     */
    public static long getFirstTimeForDate(long time) {
        return time - ((time + 8 * 60 * 60 * 1000) % (24 * 60 * 60 * 1000));
    }

    /**
     * 计算该时间对应的日期的结束时间（毫秒）（23:59:59 999）
     *
     * @param time 任意时间
     * @return
     */
    public static long getLastTimeForDate(long time) {
        return getFirstTimeForDate(time) + (24 * 60 * 60 * 1000) - 1;
    }








	/**
	 * 获取某个日期距离n天的日期（yyyy-MM-dd）
	 *
	 * @param n
	 *            可以为负数
	 * @param date
	 *            某个日期
	 * @return
	 */
	public static Date getDateTimeAddDay(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, n);
		return calendar.getTime();
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
		return sdfFormatHHMMSS.format(time - 8 * 60 * 60 * 1000);
	}

	/**
	 * 获取指定时间值的时间Format(HH:MM)
	 *
	 * @param time
	 * @return
	 */
	public static String getTimeFormatFromTimeHM(int time) {
		return sdfFormatHHMM.format(time - 8 * 60 * 60 * 1000);
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

		// 算出差额，然后日期减去所差
		return getDateTimeAddDay(dt, -w);
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

	public static Long getfirstDay(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return DateTimeUtil.getDateTimeBegin(c.getTime());
	}

	/**
	 * 获取指定时间戳后n分钟的时间
	 *
	 * @param time
	 * @param min
	 * @return
	 */
	public static Long addMin(long time, int min) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		c.add(Calendar.MINUTE, min);
		return c.getTime().getTime();
	}

	/**
	 * 根据指定格式转换时间戳
	 *
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String formatTimestamp(long time, String pattern) {
		SimpleDateFormat sdfFormat = new SimpleDateFormat(pattern);
		return sdfFormat.format(new Date(time));
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
		if(ValidUtil.isNullStr(formatDate)){
			return null;
		}
		if(ValidUtil.isNullStr(pattern)){
			pattern = format1;
		}
		return new SimpleDateFormat(pattern).parse(formatDate);
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
		return new SimpleDateFormat(formatStr).format(date);
	}

	/**
	 * 获取当前日期时间（毫秒）
	 *
	 * @return
	 */
	public static long getCurDateTime() {
		return System.currentTimeMillis();
	}

	public  static int   getCurYear(){
		Calendar now = Calendar.getInstance();
		return   now.get(Calendar.YEAR);
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
	public static Date  getMaxDateTimeAddMonth(int n, Date date) {
		Date  date1=getDateTimeAddMonth(n,date);
		Calendar calendar = Calendar.getInstance();
		/**
		 * 实例化日历各个字段,这里的day为实例化使用
		 */
		calendar.setTime(date1);
		/**
		 * Calendar.Date:表示一个月中的某天
		 * calendar.getActualMaximum(int field):返回指定日历字段可能拥有的最大值
		 */
		int  maxDay = calendar.getActualMaximum(Calendar.DATE);
		calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),maxDay,0,0,0);
		return calendar.getTime();
	}
	/**
	 * 获取某个日期的当月第一天
	 *
	 * @param n    可负数
	 * @param date
	 * @return
	 */
	public static Date  getMinDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(date.getYear(), date.getMonth(),1);
		return calendar.getTime();
	}

	/**
	 * 根据年月，生成月份开始日期。
	 *
	 * @param year   可负数
	 * @param month
	 * @return
	 */
	public static Date  getInitMonthDate(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1,1);
		return calendar.getTime();
	}
	/**
	 * 根据年月，生成月份结束日期。
	 *
	 * @param year    可负数
	 * @param day
	 * @return
	 */
	public static Date  getMaxMonthDate(int year, int month,int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1,day);
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
		Date d = getDateTimeAddDay(date,n);
		return getDateFormatFromDate(format3,d);
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
		Date d = getDateTimeAddYear(date,n);
		return getDateFormatFromDate(format3,d);
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
			beginDate = sdfFormat.parse(beginDateStr);
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
			beginDate = sdfFormat.parse(beginDateStr);
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
			beginDate = sdfFormat.parse(beginDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return new SimpleDateFormat(formatStr).format(cal.getTime());
	}

	/**
	 * 获取指定格式的时间format
	 *
	 * @return
	 */
	public static String getDateFormatFromInt(long dateInt) {
		if (dateInt == 0l)
			return "";
		return sdfFormat.format(dateInt);
	}

	/**
	 * 获取指定格式的时间format
	 *
	 * @param dateInt
	 * @param dateFormat
	 * @return
	 */
	public static String getDateFormatFromInt(long dateInt,String  dateFormat) {
		if (dateInt == 0l)
			return "";
		return new SimpleDateFormat(dateFormat).format(dateInt);
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
			beginDate = sdfFormat.parse(beginDateStr);
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
			beginDate = sdfFormat.parse(beginDateStr);
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
		return new SimpleDateFormat(formatStr).format(cal.getTime());
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
			beginDate = sdfFormat.parse(beginDateStr);
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
			beginDate = sdfFormat.parse(beginDateStr);

			time = sdfFormat.parse(dateFormat).getTime() - beginDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) (time / (24 * 60 * 60 * 1000));
	}

	public static int getDaysFromDateFormat(String dateFormat, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
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
	 * 获取指定日期的星期几
	 *
	 * @param dt
	 *
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
	 * @param inTime
	 * @return
	 */
	public static boolean isWeekDay(Integer inTime) {
		Date d = getDateFromDays(inTime);
		int week = getWeekOfDate(d);
		if(week<=4){
			return true;
		}
		return false;
	}

	/**
	 * 指定天数是否是双休日
	 * @param inTime
	 * @return
	 */
	public static boolean isRestDay(Integer inTime) {
		Date d = getDateFromDays(inTime);
		int week = getWeekOfDate(d);
		if(week==5 || week==6){
			return true;
		}
		return false;
	}

	/**
	 * 指定天数是否是工作日
	 * @param inTime
	 * @return
	 */
	public static boolean isWeekDate(String inTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(inTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int week = getWeekOfDate(date);
		if(week<=4){
			return true;
		}
		return false;
	}

	/**
	 * 指定天数是否是双休日
	 * @param inTime
	 * @return
	 */
	public static boolean isRestDate(String inTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(inTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int week = getWeekOfDate(date);
		if(week==5 || week==6){
			return true;
		}
		return false;
	}



	public static boolean compareDateSize(Date before, Date after) {
		if (before == null || after == null) {
			return false;
		}
		long dif = after.getTime() - before.getTime();
		if(dif>0l)
			return true;
		return  false;
	}
	/**
	 * 将时间戳形式的时间数据，转化成可读的日期格式
	 * @param time
	 * @param format
	 * @return
	 */
	public static String timestampFormat(String time,String format){
		if (StringUtils.isEmpty(time)) {
			return null;
		}
		if (StringUtils.isEmpty(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.parseLong(time)));
	}



	/**
	 * 獲取指定日期所在月的第一天
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
		month=month-1;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
		return  new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
	}
	/**
	 * 获取某年某月的最后一天
	 */
	public static String getFirstDayOfMonth(int year, int month) {
		month=month-1;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));
		return   new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
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
			beginDate = sdfFormat.parse(beginDateStr);
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
		return cal.getTime().getTime()/1000;
	}

	public  static int getAge(String IDCardNum){
		Calendar cal1 = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		System.out.println(IDCardNum.substring(12,14));
		cal1.set(Integer.parseInt(IDCardNum.substring(6,10)),Integer.parseInt(IDCardNum.substring(10,12)),Integer.parseInt(IDCardNum.substring(12,14)));
		return getYearDiff(today, cal1);
	}

	public  static int getYearDiff(Calendar cal, Calendar cal1){
		int m = (cal.get(cal.MONTH)) - (cal1.get(cal1.MONTH));
		int y = (cal.get(cal.YEAR)) - (cal1.get(cal1.YEAR));
		return (y * 12 + m)/12;
	}

	/**
	 * 获取指定日期前n天的日期
	 * @param specifiedDay yyyy-MM-dd
	 * @return yyyy-MM-dd
	 */
	public static String getSpecifiedDayBefore(String specifiedDay, int i){
		Calendar c = Calendar.getInstance();
		Date date=null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day=c.get(Calendar.DATE);
		c.set(Calendar.DATE,day-i);

		String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
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
		return cal.getTimeInMillis()/1000;
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


	/**
	 * 返回当前时间的字符串
	 *
	 * @param format 时间格式，可以为空，模式格式为yyyy-MM-dd HH:mm:ss
	 * @param format 当前时间
	 */
	public static String getNowDate(String format) {

		Date currentTime = new Date();

		SimpleDateFormat formatter = new SimpleDateFormat((format == null || "".equals(format)) ? format1 : format);

		return formatter.format(currentTime);

	}


	/**
	 * 获取当前日期的下n天（yyyy-MM-dd） author zhaoyong2
	 *
	 * @param n 可以为负数
	 * @return
	 */
	public static Date getNextDay(int n) {
		return getNextDay(new Date(), n);
	}

	/**
	 * 获取某个日期的下n天（yyyy-MM-dd） author zhaoyong2
	 *
	 * @param n    可以为负数
	 * @param date 某个日期
	 * @return
	 */
	public static Date getNextDay(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, n);

		return calendar.getTime();
	}

	/**
	 * 获取当前日期的下n个月（yyyy-MM-dd） author zhaoyong2
	 *
	 * @param n 可以为负数
	 * @return
	 */
	public static Date getNextMonth(int n) {
		return getNextMonth(n, new Date());
	}

	/**
	 * 获取指定日期的下n个月（yyyy-MM-dd） author zhaoyong2
	 *
	 * @param n    可以为负数
	 * @param date 指定日期
	 * @return
	 */
	public static Date getNextMonth(int n, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, n);
		return calendar.getTime();
	}


	/**
	 * 获取当前月（yyyy-MM） author 谢云
	 *
	 * @return
	 * @throws java.text.ParseException
	 */
	public static int getCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int yearNo = calendar.get(Calendar.YEAR);
		return yearNo;
	}


	/**
	 * java生成随机数字和字母组合
	 *
	 * @param length [生成随机数的长度]
	 * @return
	 */
	public static String getCharAndNumr(int length) {
		StringBuilder val = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				char c = (char) (97 + random.nextInt(26));
				while (c == 'l' || c == 'o'){
					c = (char) (97 + random.nextInt(26));
				}
				val.append(c);
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				int n = random.nextInt(10);
				while (n == 1 || n == 0){
					n = random.nextInt(10);
				}
				val.append(String.valueOf(n));
			}
		}
		return val.toString();
	}

	/**
	 * java生成随机数字
	 *
	 * @param length [生成随机数的长度]
	 * @return
	 */
	public static String generateRandomNumStr(int length) {
		StringBuilder val = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			val.append(random.nextInt(10));
		}
		return val.toString();
	}

	/**
	 * 获得近31天的日期，最少28天，最多31天。
	 *
	 * @param endDate 例如endDate="2014-11-25"
	 * @return
	 * @author Euray
	 */
	public static List<String> getInnerMonthDays(String endDate) {
		String[] str = endDate.split("-");
		int year = Integer.parseInt(str[0]);
		int month = Integer.parseInt(str[1]);
		int days = Integer.parseInt(str[2]);
		List<String> dateList = new ArrayList<String>();
		for (int i = days; i <= getMaxDayByYearMonth(year, month); i++) {
			dateList.add(str[0] + "-" + str[1] + (i < 10 ? "-0" : "-") + i);
			//System.out.println("&&&&&&&&&&&&=="+i+"=="+str[0] + "-" + str[1] + (i < 10 ? "-0" : "-") + i);
		}
		if (days == 1)   //如果1号，则返回当月就可以了。
			return dateList;
		if (month == 12) {
			month = 1;
			year++;
		} else
			month++;
		days = getMaxDayByYearMonth(year, month);
		for (int i = 1; i <= days; i++) {
			dateList.add(str[0] + (month < 10 ? "-0" : "-") + (month) + (i < 10 ? "-0" : "-") + i);
			//System.out.println("&&&&&&&&&&&&=="+i+"=="+str[0] + (month< 10 ? "-0" : "-") + (month) + (i < 10 ? "-0" : "-")+ i);
		}
		return dateList;
	}
    /* 获取三个月的信息  近四个月的  有效天数等于90天 并且是整周。 */

	public static List<String> getThreeMonthDays(String beginDate) {

		/*  获取三个月的的间隔天数 */
		//String   endDate=DateUtil.format(DateUtil.getNextMonth(3),"yyyy-MM-dd");
		//System.out.println("endDate===="+endDate);
		/*  获取两个日期的间隔天数 */
		int gapDateNum = Integer.parseInt(DateUtil.getDaysBetween(new Date(), DateUtil.getDateTimeAddMonth(3, new Date())) + "");
		//System.out.println("endDate===="+gapDateNum);
		String[] str = beginDate.split("-");
		int year = Integer.parseInt(str[0]);
		int month = Integer.parseInt(str[1]);
		int validDaysNum = -DateUtil.getGapWeekOfDate(new Date());
		int daysNum = 0;
		int days = Integer.parseInt(str[2]);
		List<String> dateList = new ArrayList<String>();
		int monthNum = 5;
		for (int j = 0; j < monthNum; j++) {
			for (int i = days; i <= getMaxDayByYearMonth(year, month); i++) {
				daysNum++;
				validDaysNum++;
				dateList.add(year + (month < 10 ? "-0" : "-") + (month) + (i < 10 ? "-0" : "-") + i);
				//System.out.println(year + "-" + (month< 10 ? "-0" : "-") + (month) + (i < 10 ? "-0" : "-") + i);
				if ((daysNum % 7 == 0) && (validDaysNum >= gapDateNum)) {
					j = 6;
					break;
				}

			}
			days = 1;
			if (month == 12) {
				month = 1;
				year++;
			} else
				month++;
		}

		return dateList;
	}

	/*最长显示4个月的数据。  */
	public static List<String> getFourMonthDays(String beginDate) {

	/*  获取三个月的的间隔天数 */
		//String   endDate=DateUtil.format(DateUtil.getNextMonth(3),"yyyy-MM-dd");
		//System.out.println("endDate===="+endDate);
	/*  获取两个日期的间隔天数 */
		int gapDateNum = Integer.parseInt(DateUtil.getDaysBetween(new Date(), DateUtil.getMaxDateTimeAddMonth(3, new Date())) + "");
		//System.out.println("endDate===="+gapDateNum);
		String[] str = beginDate.split("-");
		int year = Integer.parseInt(str[0]);
		int month = Integer.parseInt(str[1]);
		int validDaysNum = -DateUtil.getGapWeekOfDate(new Date());
		int daysNum = 0;
		int days = Integer.parseInt(str[2]);
		List<String> dateList = new ArrayList<String>();
		int monthNum = 4;
		for (int j = 0; j < monthNum; j++) {
			for (int i = days; i <= getMaxDayByYearMonth(year, month); i++) {
				daysNum++;
				validDaysNum++;
				dateList.add(year + (month < 10 ? "-0" : "-") + (month) + (i < 10 ? "-0" : "-") + i);
				//System.out.println(year + "-" + (month< 10 ? "-0" : "-") + (month) + (i < 10 ? "-0" : "-") + i);
				if ((daysNum % 7 == 0) && (validDaysNum >= gapDateNum)) {
					j = 6;
					break;
				}

			}
			days = 1;
			if (month == 12) {
				month = 1;
				year++;
			} else
				month++;
		}

		return dateList;
	}

	/* 根据某个日期，获取包括当前日期的三个月数据  */
	public static List<String> getCurrentThreeMonthDays(String beginDate) {
		String[] str = beginDate.split("-");
		int year = Integer.parseInt(str[0]);
		int month = Integer.parseInt(str[1]);
		int validDaysNum = DateUtil.getGapWeekOfDate(DateUtil.getMinDate(new Date()));
		List<String> dateList = new ArrayList<String>();
		int monthNum = 3;
		int i = 0, j = 0;
		for (j = 0; j < monthNum; j++) {
    	/*月初补空 */
			validDaysNum = DateUtil.getGapWeekOfDate(DateUtil.getInitMonthDate(year, month));
			dateList = addSpaceList(dateList, validDaysNum);
			for (i = 1; i <= getMaxDayByYearMonth(year, month); i++) {
				dateList.add(year + (month < 10 ? "-0" : "-") + (month) + (i < 10 ? "-0" : "-") + i);
			}
			validDaysNum = 6 - DateUtil.getGapWeekOfDate(DateUtil.getMaxMonthDate(year, month, i - 1));

			dateList = addSpaceList(dateList, validDaysNum);
   	  	/*月末补空 */
			if (month == 12) {
				month = 1;
				year++;
			} else
				month++;
		}

		return dateList;
	}

	private static List<String> addSpaceList(List<String> dateList, int daysNum) {
		for (int i = 0; i < daysNum; i++) {
			dateList.add("");
		}
		return dateList;
	}

/* 获取1个月的信息  */

	public static List<String> getMonthDays(String beginDate, int monthNumInt) {
	/* 可以获取1个月或者3个月的天数  */
	/*  获取两个日期的间隔天数 */
	/* 根据任何日期，判断该日期对应的周一  */
		Date tempDate = null;
		String weekBeginDate = "";

		try {
			tempDate = DateUtil.parse(beginDate, "yyyy-MM-dd");
			weekBeginDate = DateUtil.getDateFormatFromDate("yyyy-MM-dd", DateUtil.getBeginWeekOfDate(tempDate));
		} catch (ParseException pe) {
		}
		int gapDateNum = Integer.parseInt(DateUtil.getDaysBetween(tempDate, DateUtil.getDateTimeAddMonth(monthNumInt, tempDate)) + "");
		String[] str = weekBeginDate.split("-");
		int year = Integer.parseInt(str[0]);
		int month = Integer.parseInt(str[1]);
		int validDaysNum = -DateUtil.getGapWeekOfDate(tempDate);
		int daysNum = 0;
		int days = Integer.parseInt(str[2]);
		List<String> dateList = new ArrayList<String>();
		int monthNum = monthNumInt + 2;
		for (int j = 0; j < monthNum; j++) {
			for (int i = days; i <= getMaxDayByYearMonth(year, month); i++) {
				daysNum++;
				validDaysNum++;
				dateList.add(year + (month < 10 ? "-0" : "-") + (month) + (i < 10 ? "-0" : "-") + i);
				//System.out.println(year + "-" + (month< 10 ? "-0" : "-") + (month) + (i < 10 ? "-0" : "-") + i);
				if ((daysNum % 7 == 0) && (validDaysNum >= gapDateNum)) {
					j = 6;
					break;
				}

			}
			days = 1;
			if (month == 12) {
				month = 1;
				year++;
			} else
				month++;
		}

		return dateList;
	}

/* 获取4个月的信息  */

	public static List<String> getFourMonthDays(String beginDate, int monthNumInt) {
	/* 可以获取1个月或者3个月的天数  */
	/*  获取两个日期的间隔天数 */
	/* 根据任何日期，判断该日期对应的周一  */
		Date tempDate = null;
		String weekBeginDate = "";

		try {
			tempDate = DateUtil.parse(beginDate, "yyyy-MM-dd");
			weekBeginDate = DateUtil.getDateFormatFromDate("yyyy-MM-dd", DateUtil.getBeginWeekOfDate(tempDate));
		} catch (ParseException pe) {
		}
		String[] str = weekBeginDate.split("-");
		int year = Integer.parseInt(str[0]);
		int month = Integer.parseInt(str[1]);
		int days = Integer.parseInt(str[2]);
		List<String> dateList = new ArrayList<String>();
		int monthNum = monthNumInt;
		for (int j = 0; j < monthNum; j++) {
			for (int i = days; i <= getMaxDayByYearMonth(year, month); i++) {
				dateList.add(year + (month < 10 ? "-0" : "-") + (month) + (i < 10 ? "-0" : "-") + i);
				System.out.println(year + "-" + (month < 10 ? "-0" : "-") + (month) + (i < 10 ? "-0" : "-") + i);
			}
			days = 1;
			if (month == 12) {
				month = 1;
				year++;
			} else
				month++;
		}
		return dateList;
	}


	public static int getMaxDayByYearMonth(int year, int month) {
		int maxDay = 0;
		int day = 1;
		/**
		 * 与其他语言环境敏感类一样，Calendar 提供了一个类方法 getInstance，
		 * 以获得此类型的一个通用的对象。Calendar 的 getInstance 方法返回一
		 * 个 Calendar 对象，其日历字段已由当前日期和时间初始化：
		 */
		Calendar calendar = Calendar.getInstance();
		/**
		 * 实例化日历各个字段,这里的day为实例化使用
		 */
		calendar.set(year, month - 1, day);
		/**
		 * Calendar.Date:表示一个月中的某天
		 * calendar.getActualMaximum(int field):返回指定日历字段可能拥有的最大值
		 */
		maxDay = calendar.getActualMaximum(Calendar.DATE);
		return maxDay;
	}

    /*  获取当前日所在周的周一对应的日期
	 public static String getWeekOfDate(Date dt) {
	        String[] weekDays = {"星期日","星期一", "星期二","星期三", "星期四", "星期五", "星期六"};
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(dt);

	        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	        if (w < 0)
	            w = 0;

	        return weekDays[w];
	    }*/


	public static int getDateTime(Date date) {
		return (int) (date.getTime() / 1000);
	}

	/**
	 * 获取当天23:59:59的秒数
	 *
	 * @param time 当天00:00:00
	 * @return
	 */
	public static int getDayEnd(int time) {
		return time + 23 * 3600 + 59 * 60 + 59;
	}

	public static String getDateFormat(int sec, String format) {
		return DateUtil.getDateFormatFromDate(format, getDateFromSec(sec));
	}

	public static Date getDateFromSec(int sec) {
		return new Date(sec * 1000L);
	}

	public static String getToken() {
		return String.valueOf(UUID.randomUUID());
	}

	// public synchronized static Long getOrderId() {
	// RedisClient redisClient = new RedisClient();
	// String num = redisClient.incrby("") + "";
	// for (int i =0; i<6-num.length(); i++){
	// num = "0"+num;
	// }
	// String dateStr = DateUtil.format(new Date(), DateUtil.FORMAT_TIME_13);
	// return Long.parseLong(1+dateStr+num);
	// }
	//
	// public synchronized static Long getOrderDetailId() {
	// return 0L;
	// }
	//
	// public synchronized static Long getOrderDetailSessionsId() {
	// return 0L;
	// }

	/**
	 * 描 述：<br/>
	 * 作 者：王建强<br/>
	 */
	public static int getDateEndTime(Date date) {
		Calendar dateEnd = Calendar.getInstance();
		dateEnd.setTime(date);
		dateEnd.set(Calendar.HOUR_OF_DAY, 23);
		dateEnd.set(Calendar.MINUTE, 59);
		dateEnd.set(Calendar.SECOND, 59);
		dateEnd.set(Calendar.MILLISECOND, 0);
		return (int) dateEnd.getTimeInMillis() / 1000;
	}

	/**
	 * 获取指定日前的零点时间
	 *
	 * @param date Date
	 * @return long
	 */
	public static int getInitTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return (int) calendar.getTimeInMillis() / 1000;
	}

	public static int getMonthWeekNum(String strDate) {
		//字符串转成日期，求取当月最大天数。然后判断当月起始日期
	     /*  if（（当月起始日所在星期+当月最大天数)%7==0）
					 return =（当月起始日所在星期+当月最大天数）/7
			else
				 return =（当月起始日所在星期+当月最大天数）/7 +1
			*/
		String[] str = strDate.split("-");
		int year = Integer.parseInt(str[0]);
		int month = Integer.parseInt(str[1]);
		int weekNum = 0;
		int validDaysNum = DateUtil.getGapWeekOfDate(DateUtil.getInitMonthDate(year, month));//获取每月月初日对应的星期一还差天数
		//System.out.println("==="+strDate+"===validDaysNum==="+validDaysNum);
		int days = DateTimeUtil.getMaxDayByYearMonth(year, month); //获取每月最大的天数
		if ((validDaysNum + days) % 7 == 0)
			weekNum = (validDaysNum + days) / 7;
		else
			weekNum = (validDaysNum + days) / 7 + 1;

		return weekNum;
	}


	public static Date getEndDate(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}


	public static Date getfirstDate(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return c.getTime();
	}

	public static void main(String[] args) {
		String date = "2016-09";
//		System.out.println(getMinDate(new Date("201609")));
//		System.out.println(getFirstDayOfMonth(Integer.parseInt(date.split("-")[0]), Integer.parseInt(date.split("-")[1])));
//		System.out.println(getLastDayOfMonth(Integer.parseInt(date.split("-")[0]), Integer.parseInt(date.split("-")[1])));
//		System.out.println(longToTime(1571210367551L));
//		System.out.println(intToDate(1571210367551));
	}

}

/*
 *  Copyright 2014-2015 snakerflow.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package cn.com.ktm.mt.model.util.excel.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 
 * @since 0.1
 */
public class DateUtils {
	public static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

	public static String FORMAT_MD = "yyyy-MM";
	public static String FORMAT = "yyyy-MM-dd";

	/**
	 * 格式化
	 */
	public static String formatDate(Date dateTime, String format) {
		SimpleDateFormat simpledate = new SimpleDateFormat(format);

		return simpledate.format(dateTime);
	}

	/**
	 * 转换日期类型 作者 李欣 日期 20140811
	 * @throws ParseException 
	 */
	public static Date formatDate(String dateTime, String formats) throws Exception  {
		SimpleDateFormat simpledate = new SimpleDateFormat(formats);
		Date date = simpledate.parse(dateTime);
		return date;
	}

	/**
	 * 下一天
	 */
	public static Date getNextDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + n);
		return cal.getTime();
	}

	/**
	 * 前一天
	 */
	public static Date getBeforeDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - n);
		return cal.getTime();
	}

	/**
	 * 间隔天数
	 */
	public static double getIntervalDay(Date beginDate, Date endDate) {
		long beginTime = beginDate.getTime();
		long endTime = endDate.getTime();
		return (beginTime - endTime) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 获取某年月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());
		return lastDayOfMonth;
	}

	public static String getLastDayOfMonth(String year, String month) {
		return getLastDayOfMonth(Integer.parseInt(year),
				Integer.parseInt(month));
	}

	/**
	 * 获取两个日期间的月份数量
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getMonth(String startDate, String endDate) {
		Date start = null;
		Date end = null;
		try {
			start = formatDate(startDate, DateUtils.FORMAT);
			end = formatDate(endDate, DateUtils.FORMAT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (start.after(end)) {
			Date t = start;
			start = end;
			end = t;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		temp.add(Calendar.DATE, 1);

		int year = endCalendar.get(Calendar.YEAR)
				- startCalendar.get(Calendar.YEAR);
		int month = endCalendar.get(Calendar.MONTH)
				- startCalendar.get(Calendar.MONTH);

		if ((startCalendar.get(Calendar.DATE) == 1)
				&& (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month + 1 + 1;
		} else if ((startCalendar.get(Calendar.DATE) != 1)
				&& (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month + 1;
		} else if ((startCalendar.get(Calendar.DATE) == 1)
				&& (temp.get(Calendar.DATE) != 1)) {
			return year * 12 + month + 1;
		} else {
			return (year * 12 + month - 1) < 0 ? (0 + 1)
					: (year * 12 + month + 1);
		}
	}

	/**
	 * 获取两个日期之间的日期
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getDateArray(String startDate, String endDate,
			int MonthNum) throws Exception {
		List list = new ArrayList();

		startDate = startDate.substring(0, 7) + "-01";
		endDate = endDate.substring(0, 7) + "-03";

		Date c_begin = formatDate(startDate, DateUtils.FORMAT);
		Date c_end = formatDate(endDate, DateUtils.FORMAT);

		while (c_begin.before(c_end)) {
			String[] array = new String[3];

			// 开始的年份
			String yearStartStr = formatDate(c_begin, "yyyy");
			String monthStartStr = formatDate(c_begin, "MM");
			// 结束的年从份
			String yearEndStr = "";
			String monthEndStr = "";

			// 加上MonthNum 个月
			Date tmp_begin = getDateMonthAdd(c_begin, MonthNum);

			if (tmp_begin.after(c_end)) {
				yearEndStr = formatDate(c_end, "yyyy");
				monthEndStr = formatDate(c_end, "MM");
			} else {
				yearEndStr = formatDate(tmp_begin, "yyyy");
				monthEndStr = formatDate(tmp_begin, "MM");
			}
			array[0] = yearStartStr + "-" + monthStartStr + "-01";
			array[1] = getLastDayOfMonth(yearEndStr, monthEndStr);

			if ((yearStartStr + monthStartStr).equals(yearEndStr + monthEndStr)) {
				array[2] = monthStartStr;
			} else {
				array[2] = monthStartStr + " ~ " + monthEndStr;
			}

			list.add(array);

			// System.out.print("开始时期："+array[0]+"  ~ 结束时期："+array[1]+" 月份："+array[2]);
			// System.out.println("");
			if (getDateMonthAdd(tmp_begin, 1).before(c_begin)) {
				break;
			} else {
				c_begin = getDateMonthAdd(tmp_begin, 1);
			}
		}
		return list;
	}

	public static void main(String[] args) throws Exception {

		String startDate = "2014-12-01";
		String endDate = "2015-01-09";

		int monthAll = DateUtils.getMonth(startDate, endDate);
		System.out.println("monthAll=" + monthAll);
		int a = (monthAll / 12);
		System.out.println("a=" + a);

		List list = getDateArray(startDate, endDate, a);

		System.out.println("size = " + list.size());
		for (int i = 0; i < list.size(); i++) {
			String[] aa = (String[]) list.get(i);
			System.out.println(aa[0] + " ," + aa[1] + " , " + aa[2]);
		}
	}

	/**
	 * <pre>
	 * 描述  对指定日期的月份增加MonthNum个月
	 * 参数  String dateStr ,int MonthNum
	 * 返回  Date
	 * </pre>
	 */
	public static Date getDateMonthAdd(String dateStr, int MonthNum)
			throws Exception {
		Date date = null;
		try {
			date = formatDate(dateStr, DateUtils.FORMAT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getDateMonthAdd(date, MonthNum);
	}

	/**
	 * <pre>
	 * 描述  对指定日期的月份增加MonthNum个月
	 * 参数  Date date ,int MonthNum
	 * 返回  Date
	 * </pre>
	 */
	public static Date getDateMonthAdd(Date date, int MonthNum)
			throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, MonthNum);
		return calendar.getTime();
	}

	

	/**
	 *  获取前一年的日期格式
	 * @param ym 需要转换的日期
	 * @param n 需要减少的年数
	 * @return
	 * @throws Exception 
	 */
	public static Date getBeforeYear(Object ym, int n) throws Exception {
		Date date = formatDate(ym.toString(), "yyyy-MM");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - n);
		cal.set(Calendar.MONTH, date.getMonth());
		return cal.getTime();
	}
	

	/**
	 * 根据dd 日期 判断返回的 月份数
	 * 下拉框选,默认  当前日期,  如果是当前日期为某月10日前 查看前2月的数据，10日后查看上一月底数据,
	 * @descript : 4月10日前 查看2月底的数据，4月10日后 查看3月底的数据
	 * @param ym 需要转换的日期
	 * @return
	 * @throws Exception 
	 */
	public static String getDateByDay(Object ym) throws Exception {
		Date date = formatDate(ym.toString(), "yyyy-MM");
		Calendar cal = Calendar.getInstance();
		int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
		
		cal.setTime(date);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		
		if(day > 10){//如果是当前天为某月10日后查看上一月底数据,
			cal.set(Calendar.MONTH, date.getMonth() - 1);//
		}else{//某月10日前 查看前2月的数据，
			cal.set(Calendar.MONTH, date.getMonth() - 2);
		}
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));  
		
		String busiDate = DateUtils.formatDate(cal.getTime(), DateUtils.FORMAT);
		
		return busiDate;
	}
	
	/**
	 * 获取某年月的最后一天
	 * 
	 * @param ym 日期 年月格式
	 * @return yyyy-MM-DD
	 */
	public static String getLastDayOfMonthByYm(Object ym) {
		String date = "";
		if(ym != null){
			int year = Integer.valueOf(ym.toString().substring(0, 4));
			int month = Integer.valueOf(ym.toString().substring(5, ym.toString().length()));
			
			date = DateUtils.getLastDayOfMonth(ym);
		}
		return date;
	}
	
	/**
	 * 获取某年月的最后一天
	 * 
	 * @param ym
	 * @return
	 * @throws ParseException 
	 */
	public static String getLastDayOfMonth(Object ym) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();;
		try {
			date = sdf.parse(ym + "-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);

		// 格式化日期
		String lastDayOfMonth = sdf.format(calendar.getTime());
		
		return lastDayOfMonth;
	}
	
	/**
	 * 获取日期中的年
	 * 
	 * @param ym
	 * @return
	 * @throws Exception 
	 * @throws ParseException 
	 */
	public static int getDateYear(Object ym) throws Exception {
		Calendar cal = Calendar.getInstance();
		int year =  cal.get(Calendar.YEAR);
		if(ym != null){
			year = Integer.valueOf(ym.toString().substring(0, 4));
		}
		return year;
	}


	public static long getDateTimeEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(11, 23);
		cal.set(12, 59);
		cal.set(13, 59);
		cal.set(14, 999);
		return cal.getTimeInMillis();
	}

	public static long getDateTimeBegin(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		cal.set(14, 0);
		return cal.getTimeInMillis();
	}
}

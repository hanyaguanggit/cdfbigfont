package cn.com.ktm.mt.model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <p>Title: DateEntity</p>
 * <p>Description: 日期禁售</p>
 * @author	liivin@163.com
 * @date	2016年8月15日上午8:50:41
 * @version 1.0
 */
public class DateEntity {
	private String dateStr;//字符串格式日期
	private String msg;	//当天消息是否闭馆
	private int inDate;//计算后的日期
	private boolean forbidden;	//是否禁止选择操作
	public DateEntity(Date date,int inDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd E", Locale.CHINA);
		this.dateStr = f.format(date);
		this.inDate = inDate;
		if (dateStr.contains("星期一")) {
			this.forbidden = true;
			this.msg = "闭馆";
		}
	}
	public DateEntity(Date date, String format) {
		SimpleDateFormat f = new SimpleDateFormat(format, Locale.CHINA);
	}
	public String getDateStr() {
		return dateStr;
	}
	public String getMsg() {
		return msg;
	}
	public boolean isForbidden() {
		return forbidden;
	}
	public void setForbidden(boolean forbidden) {
		this.forbidden = forbidden;
	}
	public int getInDate() {
		return inDate;
	}

}

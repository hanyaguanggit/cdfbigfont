package cn.com.ktm.mt.model.enums;

/**
 * @author  zhangdongdong
 * @data 2018-1-15
 * @description: 短信模板 业务类型编码
 */
public enum SmsKeyEnum {
	SMS_CODE("6000", "验证码"),
	SMS_TICKET("1001", "购票"),
	SMS_REGIST("1002", "注册"),
	SMS_SIGN("1003", "签约申请"),
	SMS_RENEW("1004", "续签申请"),
	SMS_SIGN_AUDIT("1005", "签约审核"),
	SMS_RENEW_AUDIT("1006", "续签审核"),
	SMS_REGIST_AUDIT("1007", "注册审核"),
	SMS_RESET_PASSWORD("2001", "重置密码"),
	SMS_BLACK("2002", "黑名单");

	private String value;
	private String dsc;

	SmsKeyEnum(String value, String dsc) {
		this.value = value;
		this.dsc = dsc;
	}

	public String value() {
		return value;
	}

	public String dsc() {
		return dsc;
	}
}

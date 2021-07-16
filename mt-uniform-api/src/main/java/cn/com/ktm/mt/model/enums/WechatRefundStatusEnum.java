package cn.com.ktm.mt.model.enums;

/**
 * Created by gaocl on 16-4-11.
 */
public enum WechatRefundStatusEnum {

	SUCCESS("SUCCESS", "成功"),
	PROCESSING("PROCESSING", "退款处理中"),
	CHANGE("CHANGE", "退款异常"),
	REFUNDCLOSE("REFUNDCLOSE", "退款关闭"),
	FAIL("FAIL", "申请失败");

	private String value;
	private String dsc;

	WechatRefundStatusEnum(String value, String dsc) {
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

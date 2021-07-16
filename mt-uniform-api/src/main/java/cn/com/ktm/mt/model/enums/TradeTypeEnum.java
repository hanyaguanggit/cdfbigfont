package cn.com.ktm.mt.model.enums;

public enum TradeTypeEnum {
	PAY(1, "支付"),
	REFUND(2, "退款"),
	QUERY(3, "查询订单"),
	CANCEL(4, "取消订单");

	private int value;
	private String dsc;

	TradeTypeEnum(int value, String dsc) {
		this.value = value;
		this.dsc = dsc;
	}

	public int value() {
		return value;
	}

	public String dsc() {
		return dsc;
	}

}

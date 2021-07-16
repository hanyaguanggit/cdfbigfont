package cn.com.ktm.mt.model.enums;

public enum PayStatusEnum {

	NON(0, "未支付"),

	COMPLETE(1, "支付已完成");

	private int value;
	private String dsc;

	PayStatusEnum(int value, String dsc) {
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

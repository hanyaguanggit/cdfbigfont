package cn.com.ktm.mt.model.enums;

public enum IsSupportNotRealNameEnum {

	UNSUPORT(0, "不支持"),
	SUPORT(1, "支持");


	private int value;
	private String dsc;

	IsSupportNotRealNameEnum(int value, String dsc) {
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

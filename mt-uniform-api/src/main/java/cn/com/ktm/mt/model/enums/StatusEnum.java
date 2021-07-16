package cn.com.ktm.mt.model.enums;

public enum StatusEnum {
	YES(1, "可用"),

	NO(0, "不可用");

	private int value;
	private String dsc;

	StatusEnum(int value, String dsc) {
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

package cn.com.ktm.mt.model.enums;

public enum RealNameEnum {

	REAL(1, "严格实名"),

	NONE(2, "非实名");

	private int value;
	private String dsc;

	RealNameEnum(int value, String dsc) {
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

package cn.com.ktm.mt.model.enums;

public enum IsDeleteEnum {
	DELETE(1, "已删"),

	DEFAULT(0, "未删");

	private int value;
	private String dsc;

	IsDeleteEnum(int value, String dsc) {
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

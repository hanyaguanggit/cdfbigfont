package cn.com.ktm.mt.model.enums;

public enum SwitchEnum {
	OPEN(1, "开"),

	CLOSE(0, "关");

	private int value;
	private String dsc;

	SwitchEnum(int value, String dsc) {
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

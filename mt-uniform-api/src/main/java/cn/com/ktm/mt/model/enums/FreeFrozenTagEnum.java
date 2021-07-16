package cn.com.ktm.mt.model.enums;

public enum FreeFrozenTagEnum {
	NO(0, "未冻结"),

	YES(1, "已冻结");

	private int value;
	private String dsc;

	FreeFrozenTagEnum(int value, String dsc) {
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

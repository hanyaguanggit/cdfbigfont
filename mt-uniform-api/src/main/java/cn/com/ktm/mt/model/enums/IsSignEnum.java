package cn.com.ktm.mt.model.enums;

/**
 * 是否是签约用户
 */
public enum IsSignEnum {

	NO(0, "不是"),
	YES(1, "是");


	private int value;
	private String dsc;

	IsSignEnum(int value, String dsc) {
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

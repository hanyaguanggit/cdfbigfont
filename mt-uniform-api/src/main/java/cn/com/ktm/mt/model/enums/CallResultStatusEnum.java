package cn.com.ktm.mt.model.enums;

public enum CallResultStatusEnum {

	SUCCESS(0, "成功"),

	FAIL(1, "失败");

	private int value;
	private String dsc;

	CallResultStatusEnum(int value, String dsc) {
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

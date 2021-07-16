package cn.com.ktm.mt.model.enums;

public enum AdminUserTypeEnum {

	COMMON(0, "普通职员"),
	LEADER(1, "组长"),
	SALL(2, "正式售票员"),
	SALLTEMP(3, "临时售票员"),
	CHECK(4, "验票员"),
	SUPER(99, "超级管理员");

	private int value;
	private String dsc;

	AdminUserTypeEnum(int value, String dsc) {
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

package cn.com.ktm.mt.model.enums;

public enum AuditStatusEnum {

	DEFAULT(0, "未审核"),

	YES(1, "审核通过"),

	NO(-1, "审核失败");

	private int value;
	private String dsc;

	AuditStatusEnum(int value, String dsc) {
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

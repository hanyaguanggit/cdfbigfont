package cn.com.ktm.mt.model.enums;

public enum InvoiceStatusEnum {

	NO(0, "未开发票"),

	YES(1, "已开发票");

	private int value;
	private String dsc;

	InvoiceStatusEnum(int value, String dsc) {
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

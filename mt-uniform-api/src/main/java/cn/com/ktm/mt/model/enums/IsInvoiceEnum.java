package cn.com.ktm.mt.model.enums;

public enum IsInvoiceEnum {

	NO(0, "不开发票"),

	YES(1, "开发票");

	private int value;
	private String dsc;

	IsInvoiceEnum(int value, String dsc) {
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

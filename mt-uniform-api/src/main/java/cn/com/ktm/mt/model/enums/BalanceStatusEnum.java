package cn.com.ktm.mt.model.enums;

public enum BalanceStatusEnum {

	NO(0, "未结算"),
	YES(1, "已结算");

	private int value;
	private String dsc;

	BalanceStatusEnum(int value, String dsc) {
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

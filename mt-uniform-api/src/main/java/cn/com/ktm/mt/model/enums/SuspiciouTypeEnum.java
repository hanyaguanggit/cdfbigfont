package cn.com.ktm.mt.model.enums;

public enum SuspiciouTypeEnum {

	IP(1, "IP攻击"),

	AGENT(2, "Agent无效"),

	TOKEN(3, "Token错误");

	private int value;
	private String dsc;

	SuspiciouTypeEnum(int value, String dsc) {
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

package cn.com.ktm.mt.model.enums;

public enum CertificateTypeEnum {

	IDCARD_CHINA_MAINLAND(1, "身份证"),

	PASSPORT(2, "护照/港澳台身份证"),

	STUDENT(3, "学生证"),

	DISABILITY(4, "残疾证"),

	SOLDIER(5, "军人证"),

	NONAME(6, "非实名"),

	LEADER_IDCARD(7, "领队身份证");


	//SOLDIER(5, "军人证");
	private int value;
	private String dsc;

	CertificateTypeEnum(int value, String dsc) {
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

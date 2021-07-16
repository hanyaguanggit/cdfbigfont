package cn.com.ktm.mt.model.enums;


public enum LogTypeEnum {
	DEBUG(1, "Debug"),
	INFO(2, "Info"),
	WARN(3, "Warn"),
	ERROR(4, "Error"),
	FALT(5, "Falt");

	private int value;
	private String dsc;

	LogTypeEnum(int value, String dsc) {
		this.value = value;
		this.dsc = dsc;
	}

	public int value() {
		return value;
	}

	public String dsc() {
		return dsc;
	}

	public static String  getDecByValue(Integer value) {
		String dec="";
		if (value == null) {
			dec ="";
		} else if (LogTypeEnum.DEBUG.value() == value.intValue()) {
			dec=LogTypeEnum.DEBUG.dsc();
		} else if (LogTypeEnum.INFO.value() == value.intValue()) {
			dec=LogTypeEnum.INFO.dsc();
		} else if (LogTypeEnum.WARN.value() == value.intValue()) {
			dec=LogTypeEnum.WARN.dsc();
		} else if (LogTypeEnum.ERROR.value() == value.intValue()) {
			dec=LogTypeEnum.ERROR.dsc();
		} else if (LogTypeEnum.FALT.value() == value.intValue()) {
			dec=LogTypeEnum.FALT.dsc();
		}
		return dec;
	}

}

package cn.com.ktm.mt.model.enums;

public enum MediaTypeEnum {

	TICKET(0, "票纸"),
	ID_CARD(1, "身份证"),
	CREDENTIAL_CARD(2, "证卡");

	private int value;
	private String dsc;

	MediaTypeEnum(int value, String dsc) {
		this.value = value;
		this.dsc = dsc;
	}

	public static MediaTypeEnum getMediaTypeByValue(int value){

		for(MediaTypeEnum enumm : MediaTypeEnum.values()){
			if(value == enumm.value()){
				return enumm;
			}
		}

		return null;
	}

	public int value() {
		return value;
	}

	public String dsc() {
		return dsc;
	}

}

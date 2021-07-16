package cn.com.ktm.mt.model.enums;

public enum OrderStatusEnum {

	OUTTIME(-1, "已过期超时"),
	CANCEL(0, "已取消"),
	CREATE(1, "已创建"),
	COMPLETE(2, "已完成");

	private int value;
	private String dsc;

	OrderStatusEnum(int value, String dsc) {
		this.value = value;
		this.dsc = dsc;
	}

	public int value() {
		return value;
	}

	public String dsc() {
		return dsc;
	}

	public static OrderStatusEnum getEnumByCode(int code){
		for(OrderStatusEnum enumm : OrderStatusEnum.values()){
			if(code == enumm.value()){
				return enumm;
			}
		}
		return null;
	}

}

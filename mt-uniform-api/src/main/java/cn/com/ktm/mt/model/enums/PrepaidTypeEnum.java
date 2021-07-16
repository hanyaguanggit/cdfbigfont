package cn.com.ktm.mt.model.enums;

public enum PrepaidTypeEnum {

	CREATE(0, "创建订单"),

	 PAY(1,"支付订单");

    private int value;
    private String dsc;

    PrepaidTypeEnum(int value, String dsc) {
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

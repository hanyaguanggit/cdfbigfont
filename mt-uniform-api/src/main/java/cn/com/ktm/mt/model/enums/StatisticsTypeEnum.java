package cn.com.ktm.mt.model.enums;

public enum StatisticsTypeEnum {
    SALE(1, "销售"),

    REFUND(2, "退款");

    private int value;
    private String dsc;

    StatisticsTypeEnum(int value, String dsc) {
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

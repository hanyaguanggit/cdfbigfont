package cn.com.ktm.mt.model.enums;

public enum PriceTypeEnum {

    FREE(1, "免费"),

    HALF(2, "半价票[~,18],[60,~]"),

    STUDENT(3, "学生"),

    ADULT(4, "成人/全价"),

    OLDMANFREE(5, "老人免费票[70以上]"),

    CHIRDENFREE(6,"未成年人免费票[~,18]"),

    GUIDE(7, "导游票");

    private int value;
    private String dsc;

    PriceTypeEnum(int value, String dsc) {
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

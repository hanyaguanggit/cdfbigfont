package cn.com.ktm.mt.model.enums;

public enum LoginWayEnum {
    ALI(1, "支付宝"),
    WECHAT(2, "微信"),
    WEB(3,"WEB"),
    WAP(0, "WAP");

    private int value;
    private String dsc;

    LoginWayEnum(int value, String dsc) {
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

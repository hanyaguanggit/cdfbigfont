package cn.com.ktm.mt.model.enums;

public enum LoginType {
    NORMAL(0, "正常登录"),
    NOPASS(1, "审核未通过登录"),
    QUICKIN(2, "快捷登录"),
    PUBLIC(3, "公众号/服务号");

    private int value;
    private String dsc;

    LoginType(int value, String dsc) {
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

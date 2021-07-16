package cn.com.ktm.mt.model.enums;

public enum PayWayEnum {

    CASH(0, true, "现金"),
    WECHAT(1, false,"微信"),
    ALIPAY(2,false, "支付宝"),
    UNION(3, true,"银联"),
    MEITUAN(4, true,"美团支付"),
    CHEQUE(5, false, "支票"),
    UMS_FACE_PAY(7, true, "建行-B扫C"),
    POINT(8, false, "积分支付"),
    UMS_SCAN_PAY(9, false,  "建行-C扫B"),
    UMS_PUBLIC_PAY(11, false, "建行-公共号支付"),
    UMS_H5_PAY(12, false, "建行-H5支付"),
    PREPAID(4, false, "预存款/签单"),
    CCB_GATE(21, false, "建行卡密支付"),
    NULL(99, false, "未有支付方式");

    private int value;
    //是否支持现场
    private boolean isSupportSite;
    private String dsc;

    PayWayEnum(int value, boolean isSupportSite, String dsc) {
        this.value = value;
        this.isSupportSite = isSupportSite;
        this.dsc = dsc;
    }

    public static String findDescByValue(int value) {
        for (PayWayEnum payWayEnum : PayWayEnum.values()) {

            if (payWayEnum.value() == value) {
                return payWayEnum.dsc();
            }
        }

        return null;
    }

    public int value() {
        return value;
    }

    public boolean isSupportSite() {
        return isSupportSite;
    }

    public String dsc() {
        return dsc;
    }

    public static PayWayEnum getEnum(int value) {

        for (PayWayEnum payWayEnum : PayWayEnum.values()) {

            if (payWayEnum.value() == value) {
                return payWayEnum;
            }
        }
        return null;
    }
}

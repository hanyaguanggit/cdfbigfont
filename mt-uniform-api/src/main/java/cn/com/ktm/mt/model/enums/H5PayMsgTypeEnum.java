package cn.com.ktm.mt.model.enums;

public enum H5PayMsgTypeEnum {

    WX("WXPay.h5Pay", "微信H5支付"),
    ALIPAY("trade.h5Pay",  "支付宝H5支付"),
    UPOP("qmf.h5Pay", "银联在线无卡"),
    QUERY("query", "查询"),
    REFUND("refund", "退款"),
    SECURE_CANCEL("secureCancel", "担保撤销"),
    SECURE_COMPLETE("secureComplete", "担保完成"),
    CLOSE("close", "关闭"),
    UAC("uac.order", "银联云闪付(走银联全渠道)");

    private String value;
    private String dsc;

    H5PayMsgTypeEnum(String value, String dsc) {
        this.value = value;
        this.dsc = dsc;
    }

    public static String findDescByValue(String value) {
        for (H5PayMsgTypeEnum payWayEnum : H5PayMsgTypeEnum.values()) {

            if (payWayEnum.value().equals(value)) {
                return payWayEnum.dsc();
            }
        }

        return null;
    }

    public String value() {
        return value;
    }

    public String dsc() {
        return dsc;
    }

    public static H5PayMsgTypeEnum getEnum(String value) {

        for (H5PayMsgTypeEnum payWayEnum : H5PayMsgTypeEnum.values()) {

            if (payWayEnum.value().equals(value)) {
                return payWayEnum;
            }
        }
        return null;
    }
}

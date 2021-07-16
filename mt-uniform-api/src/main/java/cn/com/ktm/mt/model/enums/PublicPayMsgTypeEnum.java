package cn.com.ktm.mt.model.enums;

public enum PublicPayMsgTypeEnum {

    // WX("WXPay.jsPay", "微信公众号"),
    // TODO 沈阳故宫微信公众号传3 By yaoxu 2018-10-03
    WX("3", "微信公众号"),
    // ALIPAY("trade.jsPay",  "支付宝服务号"),
    // TODO 沈阳故宫支付宝服务号传3 By yaoxu 2018-10-03
    ALIPAY("3",  "支付宝服务号"),
    QMF("qmf.jspay", "全民付"),
    NOCARD("qmf.webPay", "无卡"),
    // TODO 沈阳故宫订单查询传3 By yaoxu 2018-10-03
    // QUERY("query", "查询"),
    QUERY("3", "查询"),
    // REFUND("refund", "退款"),
    // TODO 沈阳故宫订单退款传3 By yaoxu 2018-10-03
    REFUND("3", "退款"),
    SECURE_CANCEL("secureCancel", "担保撤销"),
    SECURE_COMPLETE("secureComplete", "担保完成"),
    CLOSE("close", "关闭");

    private String value;
    private String dsc;

    PublicPayMsgTypeEnum(String value, String dsc) {
        this.value = value;
        this.dsc = dsc;
    }

    public static String findDescByValue(String value) {
        for (PublicPayMsgTypeEnum payWayEnum : PublicPayMsgTypeEnum.values()) {

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

    public static PublicPayMsgTypeEnum getEnum(String value) {

        for (PublicPayMsgTypeEnum payWayEnum : PublicPayMsgTypeEnum.values()) {

            if (payWayEnum.value().equals(value)) {
                return payWayEnum;
            }
        }
        return null;
    }
}

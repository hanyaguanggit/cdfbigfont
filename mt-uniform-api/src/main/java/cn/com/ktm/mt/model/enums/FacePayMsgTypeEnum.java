package cn.com.ktm.mt.model.enums;

public enum FacePayMsgTypeEnum {

    E_CASH("E_CASH", "电子现金"),
    SOUNDWAVE("SOUNDWAVE",  "声波"),
    NFC("NFC", "NFC"),
    CODE_SCAN("CODE_SCAN", "扫码"),
    MANUAL("MANUAL", "手输");

    private String value;
    private String dsc;

    FacePayMsgTypeEnum(String value, String dsc) {
        this.value = value;
        this.dsc = dsc;
    }

    public static String findDescByValue(String value) {
        for (FacePayMsgTypeEnum payWayEnum : FacePayMsgTypeEnum.values()) {

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

    public static FacePayMsgTypeEnum getEnum(String value) {

        for (FacePayMsgTypeEnum payWayEnum : FacePayMsgTypeEnum.values()) {

            if (payWayEnum.value().equals(value)) {
                return payWayEnum;
            }
        }
        return null;
    }
}

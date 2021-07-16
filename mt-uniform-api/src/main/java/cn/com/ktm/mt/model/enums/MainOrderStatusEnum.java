package cn.com.ktm.mt.model.enums;

public enum MainOrderStatusEnum {

    PAYNOT(1, "待支付"),

    COMPLETE(2, "已支付"),

    INTO(3,"已入馆"),

    CHANGE(4,"已改签"),

    REFUND(5,"已退票"),

    CANCLE(6,"已取消");


    private int value;
    private String dsc;

    MainOrderStatusEnum(int value, String dsc) {
        this.value = value;
        this.dsc = dsc;
    }

    public static String findDescByValue(int value){
        for (MainOrderStatusEnum orderStatusEnum : MainOrderStatusEnum.values()) {

            if (orderStatusEnum.value() == value) {
                return orderStatusEnum.dsc();
            }
        }

        return null;
    }
    public int value() {
        return value;
    }

    public String dsc() {
        return dsc;
    }
}

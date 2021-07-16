package cn.com.ktm.mt.model.enums;

public enum  WeekTypeEnum {

    MONDAY(0,"星期一"),
    TUESDAY(1,"星期二"),
    WEDNESDAY(2,"星期三"),
    THURSDAY(3,"星期四"),
    FRIDAY(4,"星期五"),
    SATURDAY(5,"星期六"),
    SUNDAY(6,"星期日");

    private int value;
    private String dsc;

    WeekTypeEnum(int value, String dsc) {
        this.value = value;
        this.dsc = dsc;
    }

    public int value() {
        return value;
    }

    public String dsc() {
        return dsc;
    }

    public static String getDsc(int value) {

        for (WeekTypeEnum weekTypeEnum : WeekTypeEnum.values()) {

            if (weekTypeEnum.value() == value) {
                return weekTypeEnum.dsc();
            }
        }
        return null;
    }
}

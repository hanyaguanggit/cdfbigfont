package cn.com.ktm.mt.model.enums;

public enum BlockStatusEnum {
    FOREVER_BLOCKED(-1, "永久冻结"),

    BLOCKED(1, "冻结中"),

    UNBLOCK(2, "已解冻");

    private int value;
    private String dsc;

    BlockStatusEnum(int value, String dsc) {
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

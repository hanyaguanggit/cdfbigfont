package cn.com.ktm.mt.model.enums;


import cn.com.ktm.mt.model.util.utils.EnumKey;

/**
 * 任务通知消息枚举类
 *
 * @author yangsen  2017年6月1日 10:59:49
 * @version 0.1
 */
public enum NoticeDataStatus {

    /** 暂不处理 */
    PAUSE((byte) 5, "暂不处理"),

    /** 处理成功 */
    SUCCESS((byte) 4, "处理成功"),

    /** 开始处理 */
    START_PROCESSING((byte) 3, "开始处理"),

    /** 已入队 */
    ENQUEUE_SUCCESS((byte) 2, "已入队"),

    /** 等待入队 */
    CAN_ENQUEUE((byte) 1, "等待入队"),

    /** 处理失败（最终失败-失败次数超过限制） */
    ERROR((byte) 0, "处理失败（最终失败-失败次数超过限制）");

    private Byte   value;
    private String desc;

    NoticeDataStatus(Byte value, String desc) {
        this.value = value;
        this.desc = desc;

        KeyUtil.ENUM_KEY.addEnum(this);
    }

    public Byte value() {
        return this.value;
    }

    public String desc() {
        return this.desc;
    }


    public static NoticeDataStatus valueOf(Byte value) {
        return KeyUtil.ENUM_KEY.getEnum(value);
    }

    private static class KeyUtil {
        static EnumKey<Byte, NoticeDataStatus> ENUM_KEY = new EnumKey<Byte, NoticeDataStatus>() {
            @Override
            protected Byte getKey(NoticeDataStatus enumOje) {
                return enumOje.value;
            }
        };
    }
}

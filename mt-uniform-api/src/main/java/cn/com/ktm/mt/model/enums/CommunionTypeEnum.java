package cn.com.ktm.mt.model.enums;

/**
 * @author zhangdongdong
 * @date created in 11:56. 2018/1/17.
 * @description: 通信交流类型
 * @modified by:
 */
public enum CommunionTypeEnum {
    SEND(1, "主动发送"),
    RECEIVE(2, "接收通知"),
    CALLBACK(3, "回调");

    private Integer value;
    private String dsc;

    CommunionTypeEnum(Integer value, String dsc) {
        this.value = value;
        this.dsc = dsc;
    }

    public Integer value() {
        return value;
    }

    public String dsc() {
        return dsc;
    }
}

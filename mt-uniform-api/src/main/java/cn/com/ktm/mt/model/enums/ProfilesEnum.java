package cn.com.ktm.mt.model.enums;

/**
 * @author zhangdongdong
 * @date created in 11:56. 2018/1/17.
 * @description: 环境配置 类型
 * @modified by:
 */
public enum ProfilesEnum {
    DEV(1, "开发环境"),
    TEST(2, "测试环境"),
    PROD(3, "正式环境");

    private Integer value;
    private String dsc;

    ProfilesEnum(Integer value, String dsc) {
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

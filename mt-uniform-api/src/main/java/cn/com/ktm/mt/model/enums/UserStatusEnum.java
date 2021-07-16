package cn.com.ktm.mt.model.enums;

/**
 * 类 名: UserStatusEnum<br/>
 * 描 述: 用户状态枚举<br/>
 * 作 者: 王建强<br/>
 * 创 建： 2016年3月12日<br/>
 * <p/>
 * 历 史: (版本) 作者 时间 注释 <br/>
 */
public enum UserStatusEnum {

	ENNABLE(1, "可用"),

	INVALID(2, "不可用");

	private int value;
	private String dsc;

	UserStatusEnum(int value, String dsc) {
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

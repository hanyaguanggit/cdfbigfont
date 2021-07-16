package cn.com.ktm.mt.model.enums;

/**
 * 类 名: UserWxBindEnum {<br/>
 * 描 述: 用户微信绑定状态枚举<br/>
 * 作 者: Fushenghua<br/>
 * 创 建： 2018.06.29<br/>
 * <p/>
 * 历 史: (版本) 作者 时间 注释 <br/>
 */
public enum UserWxBindEnum {

	BIND(1, "绑定"),

	NOBIND(0, "未绑定");

	private int value;
	private String dsc;

	UserWxBindEnum(int value, String dsc) {
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

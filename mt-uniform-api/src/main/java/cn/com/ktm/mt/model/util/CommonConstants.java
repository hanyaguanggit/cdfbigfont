package cn.com.ktm.mt.model.util;

public class CommonConstants {
	public static final String RETURN_CODE = "code";
	public static final String RETURN_MSG = "msg";
	public static final String RETURN_TOKEN = "token";
	public static final String RETURN_PAGE_COUNT = "count";
	public static final String RETURN_DATA = "data";
	public static final String RETURN_DATA_DETAIL = "dataDetail";
	public static final String RETURN_KEY = "key";

	/*参数不完整**/
	public static final String ERROR_VERI_CODE = "000002";
	/*参数错误**/
	public static final String ERROR_PARAM  = "000001";
	/**支付错误*/
	public static final String ERROR_PAY_PASS   = "000004";

	/***用户已存在**/
	public static final String ERROR_USER_HAS_REGISTER = "000008";

	/**成功*/
	public static final String SUCCESS = "000000";

	/***失败*/
	public static final String VERTIFY_FAIL  = "555555";
	/***失败*/
	public static final String FALIED  = "999999";
	/**等待处理**/
	public static final String WAIT_CHECK   = "666666";

	/**结果**/
	public static final String RESULT = "result";
	/**系统功能标识*/
	public static final String BUSS_TYPE_SYSSET  = "0";//系统设置
	public static final String BUSS_TYPE_MERCHANT  = "1";//商户图片
	public static final String BUSS_TYPE_USER  = "2";//
	public static final String BUSS_TYPE_EXAMINATION  = "3";//
	public static final String BUSS_TYPE_QUESTIONNAIRE  = "4";//
	public static final String BUSS_TYPE_ARTICLE  = "5";//
	public static final String BUSS_TYPE_PLUP  = "6";//
	public static final String BUSS_TYPE_HEALTH  = "7";//
	public static final String BUSS_TYPE_EVENT  = "8";//

	/***秘密密钥**/
	public static final String SECRETKEY = "gugong-20170328";
	/**密令错误*/
	public static final String ERROR_TOKEN      = "000005";
}

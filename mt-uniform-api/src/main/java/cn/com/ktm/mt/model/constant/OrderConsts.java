package cn.com.ktm.mt.model.constant;

import java.math.BigDecimal;

/**
 * Created by 陈臻 on 2017/5/2.
 */
public class OrderConsts {
    public static String DEFAULT_WX_PASSWORD = "123456a";
    public static String DEFAULT_PRICE = "0.0";
    public static String IN_DATE_FORMATTER = "yyyy-MM-dd";
    public static String UPDATE_ORDER_SUB_ERROR_MSG = "子订单订单状态错误，操作失败！";
    public static String UPDATE_ORDER_MAIN_ERROR_MSG = "主订单订单状态错误，操作失败！";
    public static BigDecimal MONEY_UNIT_CONVERSION = new BigDecimal("100");
    public static Long PAY_DEADLINE_TIME = 18L * 60L * 1000L;
}

package cn.com.ktm.mt.model.util;

/* 
　@Author:gaopeng
  @Description:
  @Date:Creted in 18:49　2019/10/8/008　　
  　　　　　　　
*/
public class AppConstants {

    public static final String REDIS_KEY_PREFIX = "gugong:"; // redis缓存前缀

    /** redis session 前缀*/
    public static final String REDIS_SESSION_PREFIX = "gugong:session:";

    /** redis缓存 session失效时间 秒*/
    public static final int REDIS_SESSION_EXPIRE =  60*60*24*30;

    /** redis token 前缀*/
    public static final String REDIS_TOKEN_PREFIX = "gugong:token:";

    /** redis缓存 token失效时间 秒*/
    public static final int REDIS_TOKEN_EXPIRE = 60*60*24*30;

    /** redis缓存 验证码 失效时间 秒*/
    public static final int REDIS_SMS_EXPIRE = 60;

    /** redis缓存 验证码 失效时间 秒*/
    public static final int REDIS_SMS_USER_EXPIRE = 60*5;

    /** redis sms 前缀*/
    public static final String REDIS_SMS_PREFIX = "gugong:sms:";

    /** redis sms 前缀*/
    public static final String REDIS_SMS_USER_PREFIX = "gugong:count:sms:";

    /** redis 单日发送最大次数*/
    public static final int REDIS_SMS_MAX_TIME = 10;

    /** redis 当前发送次数*/
    public static final String REDIS_SMS_CURRENT_TIME = "gugong:current:times:";
}

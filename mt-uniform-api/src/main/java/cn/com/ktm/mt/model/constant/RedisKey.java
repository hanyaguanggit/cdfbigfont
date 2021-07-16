package cn.com.ktm.mt.model.constant;


import cn.com.ktm.mt.model.redis.RedisProperties;

/**
 * Redis key Created by gaocl on 16-3-7.
 */
public class RedisKey {

    public static final String INTERFACECONFIG_MAP= RedisProperties.CATEGORY_PUBLIC +"interface_config_maps";//场馆库存

    public static final String CONFIG_KEY = "redisKey";
    //demo
    public static final String TEST = "TEST_123";

    //登陆
    public static final String LOGIN_ERROR_COUNT = RedisProperties.CATEGORY_PRESELL + "login_error_count_";//登陆次数 + 用户名
    public static final String LOGIN_ERROR_LOCK_KEY = RedisProperties.CATEGORY_PRESELL + "map_login_error_lock_key";//冻结用户map
    public static final String LOGIN_ERROR_LOCK_MAPKEY = RedisProperties.CATEGORY_PRESELL + "map_login_error_lock_mapkey_";//冻结用户mapkey


    //短信队列
    public static final String SMS_QUEUE = RedisProperties.CATEGORY_PUBLIC + "List.Sms";
    //邮件队列
    public static final String MAIL_QUEUE = RedisProperties.CATEGORY_PUBLIC + "List.Smail";
    //短信验证码
    public static final String SMS_CODE_PWD = RedisProperties.CATEGORY_PRESELL + "sms_code_pwd_";//短信验证码-修改密码
    public static final String SMS_CODE_PWD_VERIFY_STATUS = RedisProperties.CATEGORY_PRESELL + "sms_code_pwd_verify_status_";//短信验证状态
    public static final String SMS_COUNT_PREFIX = RedisProperties.CATEGORY_PRESELL + "sms_count_";//短信发送次数
    public static final String SMS_CHECK_COUNT_PREFIX = RedisProperties.CATEGORY_PRESELL + "sms_check_count_";//短信验证错误次数
    public static final String SMS_CODE_PREFIX = RedisProperties.CATEGORY_PRESELL + "sms_code_";
    public static final String LOCK_SEAT_PREFIX = RedisProperties.CATEGORY_PUBLIC + "lock_seat_";
    public static final String COMMON_SCHEDULE_TICKET_PREFIX = RedisProperties.CATEGORY_PRESELL + "common_schedule_ticket_"; //非影院类门票排期前缀
    public static final String SESSION_SCHEDULE_TICKET_PREFIX = RedisProperties.CATEGORY_PRESELL + "session_schedule_ticket_";//影院类门票排期前缀
    public static final String TICKETCODE = RedisProperties.CATEGORY_PUBLIC + "TICKETCODE_";//影院类门票排期前缀

    public static final String COMMON_TICKET_PREFIX = RedisProperties.CATEGORY_PRESELL + "common_ticket_"; //非影院类购票信息前缀
    public static final String ENCRYTP_COMMON_TICKET_PREFIX = RedisProperties.CATEGORY_PRESELL + "encrytp_common_ticket_"; //非影院类购票加秘信息前缀

    public static final String COMMON_TICKET_LEADER_INFO = RedisProperties.CATEGORY_PRESELL + "common_ticket_leader_info"; //非影院类领导信息前缀

    public static final String SESSION_TICKET_PREFIX = RedisProperties.CATEGORY_PRESELL + "session_ticket_"; //影院类购票信息前缀
    public static final String ENCRYTP_SESSION_TICKET_PREFIX = RedisProperties.CATEGORY_PRESELL + "encrytp_session_ticket_"; //影院类购票加秘信息前缀


    public static final String ACCESS_TIMES_PREFIX = RedisProperties.CATEGORY_PRESELL + "access_times_"; //1秒内访问次数前缀

    public static final String REDIS_CHANNEL_SUSPICIOUS = RedisProperties.CATEGORY_PRESELL + "pubsub_channel_suspicious"; //redis发布订阅channel

    public static final String SEASONS = RedisProperties.CATEGORY_PUBLIC + "map_seasons"; //淡旺季
    public static final String FACTOR = RedisProperties.CATEGORY_PUBLIC + "map_factor"; //加密因子
    public static final String TICKET_COUNT_CHANGE = RedisProperties.CATEGORY_PUBLIC + "map_ticket_count_change"; //门票库存变化，mapkey是门票id,value是可正负的int

    public static final String LOG_QUEUE = RedisProperties.CATEGORY_PUBLIC + "List.Log"; //日志
    public static final String SYS_SETS = RedisProperties.CATEGORY_PUBLIC + "map_sys_sets"; //系统参数设置

    public static final String SESSION_RESERVELIMITCOUNT= RedisProperties.CATEGORY_PUBLIC +"session_reservelimitcount";//场馆预留库存
    public static final String SESSION_LIMITCOUNT=RedisProperties.CATEGORY_PUBLIC +"session_limitcount_";//场馆库存

    public static final String TODAY_SELL_TICKET_TIME = RedisProperties.CATEGORY_PRESELL + "today_sell_ticket_time_";//当日票销售时间限制

    public static String getkey(String perfix, String flag) {
        return String.format("%s%s", perfix, flag);
    }

    //add by chenzhen 添加门票实名类型 2018.6.30
//    public static final String TICKET_REAL_NAME_TYPE = RedisProperties.CATEGORY_PUBLIC + "ticket_real_name_type";

    //add by chenzhen 添加退款截止日 2018.6.30
    public static final String REFUND_DEADLINE_DATE = "refundDeadLine";
    public static final String REFUND_AFTER_DAYS = "refundAfterDays";
    //add by chenzhen 添加预售天数日 2018.7.7
    public static final String PRESELL_CODE = "PreSellDay";


}

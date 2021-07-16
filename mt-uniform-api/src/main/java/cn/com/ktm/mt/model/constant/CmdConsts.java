package cn.com.ktm.mt.model.constant;

/**
 * Created by 陈臻 on 2017/4/27.
 */
public class CmdConsts {
    // 取消订单请求的cmd
    public static final String CANCEL_ORDER_CMD = "CN_CANCEL_ORDER";

    // 支付订单请求的cmd
    public static final String PAY_ORDER_CMD = "CN_PAY_ORDER";
    // 创建订单请求的cmd
    public static final String CREATE_ORDER_CMD = "CN_CREATE_ORDER";
    // 离线创建订单请求的cmd
    public static final String OFFLINE_ORDER_UPLOAD = "OFFLINE_ORDER_UPLOAD";
    // 下单前校验订单的cmd
    public static final String CHECK_ORDER_CMD = "CN_CHECK_ORDER_CMD";
    // 门票提交订单的cmd
    public static final String TIKCET_CHECK_ORDER_CMD = "CN_TICKET_CHECK_ORDER";

    // 查询订单请求的cmd
    public static final String QUERY_ORDER_CMD = "CN_QUERY_ORDER";
    // 查询订单请求的cmd
    public static final String QUERY_ORDER_XC_CMD = "CN_QUERY_ORDER_XC";

    // 退款申请请求的cmd
    public static final String REFUND_APPLY_CMD = "CN_REFUND_APPLY";

    // 退款处理的cmd
    public static final String REFUND_PROCESS_CMD = "CN_REFUND_PROCESS";

    // 结算cmd
    public static final String BALANCE_CMD = "BALANCE";

    // 结算cmd
    public static final String BALANCE_QUERY_CMD = "QUERY_BALANCE";

    // 退款通知请求的cmd
    public static final String REFUND_NOTIFY_CMD = "REFUND_NOTIFY";

    // 退款通知请求的cmd
    public static final String QUERY_STORE_CMD = "CN_QUERY_STORE";
    /**
     * 销售门票详情
     */
    public static final String QUERY_TICKET_SALE = "QUERY_TICKET_SALE";

    public static final String STORE_TICKET_GET = "STORE_TICKET_GET";

    public static final String STORE_INV_VENUE_GET = "STORE_INV_VENUE_GET";

    public static final String ORG_REG = "MEMBER_ORGANIZATION_INSERT";

    // 推送场次请求的cmd
    public static final String PUSH_SESSION_CMD = "PUSH_SESSION";

    // 验票通知请求的cmd
    public static final String PUSH_CHECK_TICKET_CMD = "CN_PUSH_CHECK_TICKET";

    // 推送场地信息的cmd
    public static final String PUSH_STADIUM_INFO = "PUSH_STADIUM_INFO";

    public static final String REST_PUBLIC_KEY_CMD = "CN_RESET_KEY";

    public static final String PUSH_CHANGE_TICKET_CMD = "CN_PUSH_CHANGE_TICKET";


    public static final String QUERY_SEAT_CMD = "QUERY_SEAT";

    public static final String QUERY_TICKET_CMD = "CN_QUERY_TICKET_SELF";

    public static final String OTA_QUERY_TICKET_CMD = "CN_QUERY_TICKET";


    public static final String WEB_QUERY_ORDER_LIST_CMD = "WEB_QUERY_ORDER_LIST";

    /**
     * 获取公告列表list
     */
    public static final String BASIC_ANNOUNCEMENT_LIST = "BASIC_ANNOUNCEMENT_LIST";

    /**
     * 根据id获取公告信息
     */
    public static final String BASIC_ANNOUNCEMENT_GET = "BASIC_ANNOUNCEMENT_GET";

    /**
     * 获取有效场馆信息
     */
    public static final String BASIC_VENUE_LIST = "BASIC_VENUE_LIST";

    /**
     * 获取用户相关信息
     */
    public static final String USER_CONTACT_GET = "MEMBER_CONTACT_LIST";

    public static final String USER_CONTACT_ADD = "MEMBER_CONTACT_ADD";

    public static final String USER_GET = "MEMBER_USER_GET";

    //会员密码修改
    public static final String USER_PWD_UPDATE = "MEMBER_USER_UPDATEPWD";
    //会员密码重置
    public static final String USER_PWD_RESET = "MEMBER_USER_RESETPWD";
    //会员注册
    public static final String USER_REG = "MEMBER_USER_ADD";
    //会员微信绑定
    public static final String USER_BINDWECHAT = "MEMBER_USER_BINDWECHAT";

    public static final String USER_BIND = "MEMBER_USER_BIND";

    /**
     * 获取短信模板
     */
    public static final String BASIC_SMSTEMPLATE_GET = "BASIC_SMSTEMPLATE_GET";
    /**
     * 获取服务器时间
     */
    public static final String BASIC_TIME_GET = "BASIC_TIME_GET";
    /**
     * 获取待更新系统版本
     */
    public static final String BASIC_VERSION_GET = "BASIC_VERSION_GET";
    /**
     * 更新场馆设备状态
     */
    public static final String BASIC_VENUEDEVICE_UPDATE = "BASIC_VENUEDEVICE_UPDATE";
    /**
     * 获取设备心跳
     */
    public static final String BASIC_VENUEDEVICE_BREATH = "BASIC_VENUEDEVICE_BREATH";

    /**
     * 获取支付服务接口
     */
    public static final String BASIC_TRADESERVICE_QUERY = "BASIC_TRADESERVICE_QUERY";

    /**
     *
     */
    public static final String BASIC_DICT_QUERY = "BASIC_DICT_QUERY";

    public static final String STORE_SESSION_UPDATE = "STORE_SESSION_UPDATE";
    //更新库存信息
    public static final String STORE_UPDATE = "STORE_UPDATE";

    public static final String USER_INFO_UPDATE = "MEMBER_USER_UPDATE";

    public static final String ORG_INFO_UPDATE = "MEMBER_ORGANIZATION_UPDATE";

    public static final String ORG_CONTACT_UPDATE = "MEMBER_ORGANIZATION_CONTACT_UPDATE";

    public static final String UPDATE_CONTACT_USER_INFO = "MEMBER_CONTACT_UPDATE";

    public static final String DELETE_CONTACT_USER_INFO = "MEMBER_CONTACT_DELETE";


    public static final String MEMBER_CHANNEL_GET = "MEMBER_CHANNEL_GET";

    /**
     * 查询渠道列表信息
     */
    public static final String MARKET_CHANNEL_QUERY = "MARKET_CHANNEL_QUERY";

    public static final String CHANGER_ORDER_SCHEDULE = "CHANGER_ORDER_SCHEDULE";

    public static final String CHANGER_ORDER = "CHANGER_ORDER";

    //根据条件查询门票信息
    public static final String QUERY_TICKET_LIST_INFO = "QUERY_TICKET_LIST_INFO";

    //查询黑名单用户信息
    public static final String QUERY_BLACK_USER_INFO = "MEMBER_USERBLACK_QUERY";

    public static final String QUERY_BLACK_ORG_INFO = "MEMBER_ORGANIZATIONBLACK_QUERY";

    //查询所有门票
    public static final String QUERY_All_TICKET_INFO = "QUERY_All_TICKET_INFO";

    public static final String QUERY_TICKET_SELECTIVE_INFO = "QUERY_TICKET_SELECTIVE_INFO";

    //查询场馆排期信息
    public static final String QUERY_VENUE_SCHEDULE = "QUERY_VENUE_SCHEDULE";

    //查询指定的日期段查询场次库存信息
    public static final String QUERY_SESSIONS_SCHEDULE = "QUERY_SESSIONS_SCHEDULE";

    //查询获取所有场次可售门票的列表
    public static final String QUERY_SESSIONTicket_SCHEDULE = "QUERY_SESSIONTicket_SCHEDULE";


    //查询淡旺季信息
    public static final String QUERY_SEASON_INFO = "QUERY_SEASON_INFO";

    //查询所有放映内容信息
    public static final String QUERY_SESSIONVIDEO_INFO = "QUERY_SESSIONVIDEO_INFO";

    /**
     * 根据条件获取团队信息
     */
    public static final String MEMBER_ORGANIZATION_QUERY = "MEMBER_ORGANIZATION_QUERY";

    /**
     * 根据id获取团队信息
     */
    public static final String MEMBER_ORGANIZATION_GET = "MEMBER_ORGANIZATION_GET";

    /**
     * 更新渠道预存款
     */
    public static final String UPDATE_CHANNEL_BALANCE = "MEMBER_UPDATE_CHANNEL_BALANCE";

    public static final String AUTHORITY_MENU_LIST = "AUTHORITY_MENU_LIST";

    public static final String AUTHORITY_USER_LOGIN = "AUTHORITY_USER_LOGIN";

    public static final String AUTHORITY_USER_LIST = "AUTHORITY_USER_LIST";

    public static final String AUTHORITY_USER_MENU_LIST = "AUTHORITY_USER_MENU_LIST";

    public static final String AUTHORITY_USERPASSWORD_UPDATE = "AUTHORITY_USERPASSWORD_UPDATE";

    /**
     * 获取用户可售卖门票信息
     */
    public static final String AUTHORITY_USER_TICKET_LIST = "AUTHORITY_USERTICKET_LIST";

    public static final String AUTHORITY_ROLE_LIST = "AUTHORITY_ROLE_LIST";

    public static final String AUTHORITY_ALL_ROLE_MENU_LIST = "AUTHORITY_ALL_ROLE_MENU_LIST";

    //陈臻添加打印出票/重打印出票的后台接口的关键字 2018.7.6
    public static final String PRINT_ORDER = "PRINT_ORDER";

    //陈臻添加打印出票/重打印发票的后台接口的关键字 2018.7.20
    public static final String PRINT_INVOICE = "PRINT_INVOICE";

    //票纸申领
    public static final String BASIC_PAPER_APPLY = "BASIC_PAPER_APPLY";

    //可用票纸查询
    public static final String BASIC_USED_PAPER_QUERY = "BASIC_USED_PAPER_QUERY";

    //票纸登记接口
    public static final String BASIC_PAPER_USING_RECORD = "BASIC_PAPER_USING_RECORD";
}

package cn.com.ktm.mt.model.constant;

import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.exception.AssertError;
import cn.com.ktm.mt.model.exception.ResCodeEntity;
import cn.com.ktm.mt.model.util.utils.ValidUtil;


import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by hyg on 2021/07/12.
 */
public class ResponseConsts {


    private static final HashMap<Integer, String> descriptions = new HashMap<Integer, String>();
    private static final String DEFAULT_DESC = "默认描述";


    static {
        Field[] fields = ResponseConsts.class.getDeclaredFields();

        for (Field field : fields) {
            ResponseCode annotation = field.getAnnotation(ResponseCode.class);
            if (annotation != null) {
                ResponseCode desc = (ResponseCode) annotation;
                try {
                    descriptions.put(field.getInt(null), desc.value());
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //公共的错误码
    @ResponseCode("成功")
    public static final int SUCCESS = 200;

    @ResponseCode("失败")
    public static final int ERROR = 300;

    @ResponseCode("系统内部错误，请联系场馆技术人员")
    public static final int SYSTEM_ERROR = 30000;


    @ResponseCode("网络通讯中，请稍后...")
    public static final int SERVICEERROR = 999;

    @ResponseCode(value = "请求参数错误，请查看接口文档重新发起请求")
    public static final int PARAM_ERROR = 201;

    @ResponseCode(value = "其他错误")
    public static final int PRICE_OTHER_ERROR = 299;
    @ResponseCode(value = "参数错误")
    public static final int TICKET_PARAM_ERROR = 101;
    @ResponseCode(value = "请求超时")
    public static final int TICKET_SYSTEM_CONNECT_EXCEPTION = 1022;

    @ResponseCode(value = "其他")
    public static final int TICKET_OTHER_ERROR = 199;
    public static final int CANCEL_ORDER_ERROR = 300;


    @ResponseCode(value = "当前日期没有价格日历")
    public static final int CO_INDATE_RANGE_ERR = 422;

    @ResponseCode(value = "其他")
    public static final int CO_PARAM_ERROR = 499;

    @ResponseCode(value = "合作方没有该订单")
    public static final int TICKET_ORDER_ERROR = 302;
    @ResponseCode(value = "其他")
    public static final int TICKET_QUERY_ERROR = 399;


    @ResponseCode(value = "系统商没有该订单")
    public static final int QUERY_ORDER_NOTFOUND = 302;

    @ResponseCode(value = "规则校验失败")
    public static final int CHANNEL_QUERY_ERROR = 410;
    @ResponseCode(value = "没有库存")
    public static final int CHANNEL_STORE_ERROR = 420;

    @ResponseCode(value = "备用金扣款异常")
    public static final int CHANGEORDER_fund_ERROR = 403;


    @ResponseCode(value = "所选新的日期无库存")
    public static final int CHANGEORDER_NOT_STORE_ERROR = 331;


    //  查询订单code码
    @ResponseCode(value = " 系统商没有渠道订单id")
    public static final int QUERY_ORDER_NOT_CHANNEL_ERROR = 301;

    @ResponseCode(value = " 系统商没有该订单")
    public static final int QUERY_ORDER_NOT_ERROR = 302;

    @ResponseCode(value = "去系统商查询订单超时")
    public static final int QUERY_ORDER_TIMEOUT_ERROR = 303;

    @ResponseCode(value = " 其他")
    public static final int 开始时间  = 399;



   //同步订单
    @ResponseCode(value = "参数错误 检查参数的合法性")
    public static final int SYNC_ORDER_PARAM_ERROR = 101;

    @ResponseCode(value = "系统异常 调⽤⽅重试")
    public static final int SYNC_ORDER_SYSTEM_ERROR = 102;


    @ResponseCode(value = "其他，根据describe中信息处理，或者联系运营⼈员")
    public static final int SYNC_ORDER_OTHER_ERROR = 199;


    //用户相关
    @ResponseCode(value="短信验证码相关验证失败")
    public static final int MEMBER_SMSCODE_ERROR = 902;
    @ResponseCode(value="图形验证码校验失败")
    public static final int MEMBER_VALIDCODE_ERROR = 904;
    @ResponseCode(value="电话号码验证失败")
    public static final int MEMBER_PHONE_ERROR = 905;
    @ResponseCode(value="账号已被注册")
    public static final int MEMBER_ACCOUNT_IS_EXIST = 907;
    @ResponseCode(value="参数校验失败")
    public static final int MEMBER_PARAM_CONTACT_ERROR = 908;
    @ResponseCode(value="系统异常")
    public static final int MEMBER_SYSTEM_EXCEPTION = 909;
    @ResponseCode(value="团队登录账号或者密码错误")
    public static final int MEMBER_ACCOUNT_PASSWORD_ERROR = 910;
    @ResponseCode(value="审核信息校验失败")
    public static final int MEMBER_AUDIT_INFORMATION_ERROR = 911;
    @ResponseCode(value="找不到相应⽤户信息")
    public static final int MEMBER_USERINFO_NOT_EXIST = 921;
    @ResponseCode(value="参数错误")
    public static final int MEMBER_PARAM_ERROR = 922;
    @ResponseCode(value="发送频次过⾼")
    public static final int MEMBER_SMSCODE_LIMIT = 951;
    @ResponseCode(value="电话号码⿊名单")
    public static final int MEMBER_SMSCODE_BLACKLIST = 956;

    @ResponseCode(value="参数错误")
    public static final int CONSUME = 701;

    //特殊团体订单相关
    @ResponseCode(value="系统异常，系统忙稍后处理")
    public static final int ORDER_SYSTEM_EXCEPTION= 771;

    public static final int ORDER_GOURP_NOT_EXIST = 796;
    @ResponseCode(value="分⻚请求不合法")
    public static final int ORDER_PAGE_PARAM_ERROR = 797;

    @ResponseCode(value="日期无效")
    public static final int LEVEL_MAPPING_DATE_ERROR = 802;
    @ResponseCode(value="解析日期错误")
    public static final int PARSE_DATE_ERROR = 803;
    @ResponseCode(value = "支付订单错误")
    public static final int PAY_ORDER_ERROR = 000;

    //全量层级映射关系同步
    @ResponseCode(value=" 参数错误")
    public static final int LEVEL_MAPPING_PAGE_INDEX_ERROR = 666;
    @ResponseCode(value = "id错误")
    public static final int LEVEL_MAPPING_PRODUCT_ID_ERROR = 664;
    @ResponseCode(value="数量错误")
    public static final int LEVEL_MAPPING_PAGE_SIZE_ERROR = 665;

    @ResponseCode(value="其他退款订单")
    public static final int REFUND_ORDER_OTHER =501;


    //网站模块相关参数
    @ResponseCode(value="网站结构id为空")
    public static final int SITE_STRUCTURE_ID_EMPTY_ERROR = 103;

    @ResponseCode(value="位置类型id为空")
    public static final int POSITION_TYPE_ID_EMPTY_ERROR = 104;

    @ResponseCode(value="创建位置实体异常")
    public static final int CREATE_POSITION_ERROR = 105;

    @ResponseCode(value = "创建模块实体异常")
    public static final int CREATE_SECTION_ERROE = 106;

    @ResponseCode(value ="创建图片链接失败")
    public static final int CREATE_PICTURE_LINK_ERROR = 107;

    @ResponseCode(value = "网站结构下无所属模块")
    public static final int CS_SITE_STRUCTURE_SECTION_EMPTY = 108;

    @ResponseCode(value = "网站结构模块id为空")
    public static final int SECTION_ID_NOT_EXSIT_ERROR = 109;

    @ResponseCode(value = "link_tab值为空")
    public static final int LINK_TAB_NOT_EXSIT_ERROR = 110;

    @ResponseCode(value = "创建文字链接失败")
    public static final int CREATE_TEXT_LINK_ERROR = 111;

    @ResponseCode(value = "创建富文本失败")
    public static final int CREATE_RICH_TEXT_ERROR = 112;

    @ResponseCode(value = "创建推荐商品失败")
    public static final int CREATE_RECOMMEND_PRODUCT_ERROR = 113;

    @ResponseCode(value = "创建推荐品牌失败")
    public static final int CREATE_RECOMMEND_BRAND_ERROR = 114;

    @ResponseCode(value = "创建搜索热词失败")
    public static final int CREATE_SEARCH_HOT_WORDS_ERROR = 115;

    static {
        Field[] fields = ResponseConsts.class.getDeclaredFields();

        for (Field field : fields) {
            ResponseCode annotation = field.getAnnotation(ResponseCode.class);
            if (annotation != null) {
                ResponseCode desc = (ResponseCode) annotation;
                try {
                    descriptions.put(field.getInt(null), desc.value());
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据value返回响应的响应码描述
     *
     * @param value
     * @return
     */
    public static ResCodeEntity getCodeInfo(int value) {
        return convertReturnCodeByValue(value);
    }



    /**
     * 根据code值查找出返回的对象属性值
     *
     * @param value
     * @return
     */
    private static ResCodeEntity convertReturnCodeByValue(int value) {
        String desc = descriptions.get(value);

        if (ValidUtil.isNullStr(desc)) {
            desc = DEFAULT_DESC;
        }

        return new ResCodeEntity(value, desc);
    }
  

    public static String getCodeDesc(int value, Object... params) {
        String desc = descriptions.get(value);
        if (desc != null && params != null && desc.indexOf("%s") >= 0) {
            desc = String.format(desc, params);
        }
        return desc;
    }


    public static void main(String[] args) {

        try {
//            Assert.notNull(null,ResponseConsts.PARAM_ERROR,"证件不能为空");
//            Assert.notNull(null,ResponseConsts.PARAM_ERROR,"%s证件不能为空","身份证");
//            Assert.notNull(null,ResponseConsts.CT_ORDER_ERROR,"","123456");
            Assert.fail(ResponseConsts.PARAM_ERROR, "退款截止时间参数解析异常");
        } catch (AssertError e) {
            System.out.println(e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


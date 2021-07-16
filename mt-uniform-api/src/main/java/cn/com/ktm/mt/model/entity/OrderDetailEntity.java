package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailEntity implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;

    private Long       orderId;
    private Long       ticketId;//0=普通票，1=通票，2=年票（这里没用），3=免费票（这里没用）。
    private BigDecimal actualPrice;//经过销售策略处理后的实际票价。
    private Long       inUserId;//预售网站订单中，购买人的Id。
    private String     inName;
    private String     inUserCardNo;
    private Integer    inUserCertificateType;//1=身份证，2=护照，3=学生证，4=残疾证，5=军人证，6=非实名制。如果为身份证，那么【用户身份证】和【用户证件号】都要保存。否则【用户身份证】留空。
    private String     inUserCertificate;//比如导游证等。
    private Integer    personCount;//该票纸可入场人数。
    private Integer    firstInitialInDate;
    private Integer    initialInDate;
    private Long       inTime;
    private Integer    orderDetailStatus;//2已完成(验票)，1已创建（默认）,0已取消,-1已过期超时
    private Integer    payStatus;//0=未支付，1=支付已完成
    private Integer    refundStatus;//0=未退款， 1退款申请中， 2退款审核失败 ，3=提交中（向支付宝发起http请求），4=已提交（通知支付宝成功），5=已成功（Notify返回结果），6=已失败（Notify返回结果）, 7退款过期
    private String     reason;//该数据要提交到支付宝退款接口，支付宝“退款理由”长度不能大于 256 字节，“退款理由”中不能有“ ^”、“ |”、“ $”、“ #”等影响 detail_data 格式的特殊字符
    private String     auditRemark;
    private Integer    changeTimeStatus;//该订单是否被预售网站改签，每笔订单只可改签一次。0=未改签，1=同步中，2=已成功，3=已失败。
    private Integer    isTransfer;//1需要 0不需要（默认）
    private Integer    transferStatus;//-1=不打款，0=未打款，1=已打款。
    private String     outOrderDetailId;
    private Integer    isReport;//0=不纳入统计，1=纳入统计。
    private Integer    isPrint;//退票、重打专用。
    private Integer    inStatus;//0未入馆（默认）, 1已入馆
    private Long       id;
    private Long       createUser;
    private Long       createTime;
    private Long       updateUser;
    private Long       updateTime;
    private String     ip;
    private Integer    status;//1可用（默认）,0不可用
    private Integer    isDelete;//1已删除, 0未删除（默认）
    private String     remark;
    private Integer    isVideoCupon;//1使用 0未使用（默认）
    private Integer    isVip;//1使用 0未使用（默认）
    private String     paperCode;
    private String     beforePaperCode;
    private Integer    isPrintTicket;//0未出票 1已出票
    private String     ticketName;  //门票名称。

    private Long    actualInTime;
    private Integer deviceid;
    private Long    channelId;//渠道Id
    private String  subChannelOrderId;//渠道子订单Id
    private Long    authVenue;//渠道Id
    private String  finishVenue;//渠道子订单Id
    private Long    refundTime;//渠道子订单Id
    private Long    refundFinishTime;//渠道子订单Id
    private Integer isSendCheck;//渠道子订单Id

    private int        count;
    private String     detailIds;
    private BigDecimal sumTotal;

    private Integer refundType;
    private Long    venueId;

    private Long printUser;
    private Long printTime;

    // add by chenzhen 学生证号，入馆结束日期，第一次入馆结束日期
    private Integer    firstInitialInEndDate;
    private Integer    initialInEndDate;
    private String studentNo;

    private Integer accountState;

    //陈臻添加 用户订单详情页的子订单状态
    private Integer subOrderStatus;
    //陈臻添加 用户订单详情页的子订单状态显示
    private String  subOrderStatusStr;

    private int offLine = 0; //0=在线 1=离线 这里指定了默认值只有不要删除

    private String refundReason;
    private String refundUserName;
    private Long   refundCheckUser;
    private Long   refundUser;
    private Integer priceType; //票价类型
    private Long levelId; //层级Id
    private Integer startTime; //层级Id
    private Integer endTime; //层级Id
}

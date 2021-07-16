package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailSessionsEntity implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;

    private Long       orderId;
    private Long       ticketId;//0=普通票，1=通票，2=年票（这里没用），3=免费票（这里没用）。
    private BigDecimal actualPrice;//经过销售策略处理后的实际票价。
    private Integer    beginDate;
    private Integer    endDate;
    private Integer    beginTime;//暂时不用。
    private Integer    endTime;//暂时不用。
    private Long       sessionsId;//场次
    private Long       seatId;
    private String     seatDetail;
    private Integer    orderDetailStatus;//2已完成(验票)，1已创建（默认）,0已取消,-1已过期超时
    private Integer    payStatus;//0=未支付，1=支付已完成
    private Integer    refundStatus;//0=未退款， 1退款申请中， 2退款审核失败 ，3=提交中（向支付宝发起http请求），4=已提交（通知支付宝成功），5=已成功（Notify返回结果），6=已失败（Notify返回结果）, 7退款过期
    private String     reason;//该数据要提交到支付宝退款接口，支付宝“退款理由”长度不能大于 256 字节，“退款理由”中不能有“ ^”、“ |”、“ $”、“ #”等影响 detail_data 格式的特殊字符
    private String     auditRemark;
    private Integer    isTransfer;//1需要 0不需要(默认)
    private Integer    transferStatus;//-1=不打款，0=未打款，1=已打款。
    private Integer    isReport;//0=不纳入统计，1=纳入统计。
    private Integer    inStatus;//0未入馆, 1已入馆
    private Long       inTime;
    private Integer    isPrint;//退票、重打专用。
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
    private String     inUserCardNo;
    private Integer    inUserCertificateType;
    private String     inUserCertificate;
    private String     inName;
    private Integer    isPrintTicket;//0未出票 1已出票
    private Integer    deviceid;

    private String     ticketName;
    private String     videoName;
    private Integer    count;
    private String     sessionIds;
    private BigDecimal sumTotal;
    private Long       channelId;//渠道id
    private String     subChannelOrderId;//渠道订单id

    private Long    inUserId;
    private Long    refundTime;
    private Long    refundFinishTime;
    private Integer isSendCheck;
    private Long venueId;
    private Long     printTime;

    // add by chenzhen 学生证号，入馆结束日期，第一次入馆结束日期
    private int        firstInitialInEndDate;
    private int        initialInEndDate;
    private String     studentNo;

    private Integer accountState;
}

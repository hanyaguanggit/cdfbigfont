package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;
    private String    partnerId;
    private Integer    orderChannel;
    private Integer    orderType;
    private Long       orderTime;
    private Long       orderUserId;
    private Integer    orderUserType;
    private String     orderUserName;
    private Integer    appointmentCount;
    private Integer    actualCount;
    private BigDecimal orderTotal;
    private BigDecimal payTotal;
    private BigDecimal refundTotal;
    private String     contactName;
    private String     contactCardNo;//联系人证件号
    private String     phone;
    private String     orderPhone;
    private Integer    isInvoice;
    private Integer    isCompony;
    private String     invoiceTitle;
    private Integer    invoiceStatus;
    private Integer    payStatus;
    private Long       payTime;
    private Integer    payWay;
    private String     payRemark;
    private String     payCheckNo;
    private String     tradeOrderId;
    private String     ticketCode;
    private String     orderCode;
    private String     outOrderId;
    private Integer    inTime;//入馆开始时间
    private Integer    inEndDate;//入馆结束时间
    private String     ticketInfo;
    private Integer    inStatus;
    private Integer    isMoneyCoupon;
    private Integer    signScale;
    private Long       id;
    private Long       createUser;
    private Long       createTime;
    private Long       updateUser;
    private Long       updateTime;
    private String     ip;
    private Integer    status;
    private Integer    isDelete;
    private String     remark;
    private Integer    personCount;
    private Long       deviceId;
    private boolean    noLimit;
    private Long       channelId;
    private String     channelOrderId;

    //外部交易流水号 add by chenzhen 2018.6.6 用于wap web 现场的支付流水号保存，渠道该字段为空
    private String outTradeOrderId;

    private Integer mediaType;
    //add by chenzhen 2018.7.19 用于验票的类型选择
    private Integer checkTicketType;

    //add by chenzhen 2018.7.19 发票打印时间,开发票的现场人员
    private Long invoiceTime;
    private Long invoiceUser;

    private Integer orderPeriodType; //0 全天 1上午 2下午

    private Integer accountState;

    private Integer ticketClass;

    private String          teamPlanFile;
    private String          contactCardType;
    
    


}
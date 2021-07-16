package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundEntity implements Serializable {
    private static final long serialVersionUID = -580978257827294219L;

    private Long       id;
    private Long       channelId;
    private Long       orderId;
    private String     refundBatchNo;
    private String     orderDetails;//格式： 子订单ID:订单类别,子订单ID:订单类别
    private String     refundTicketInfo;
    private BigDecimal total;
    private Long       applyUserId;
    private Integer    applyUserType;
    private String     refundReason;
    private Long       auditUserId;
    private Integer    auditUserType;
    private Integer    auditStatus;
    private String     reason;
    private Long       auditTime;
    private Long       createUser;
    private Long       createTime;
    private Long       updateUser;
    private Long       updateTime;
    private String     ip;
    private Integer    status;
    private Integer    isDelete;
    private String     devRemark;
    private String     remark;
    private Long       refundTime;
    private Integer    refundType;
    private Integer    isForce;
    private Integer    notifyStatus;
    private String     tradeOrderId;
}

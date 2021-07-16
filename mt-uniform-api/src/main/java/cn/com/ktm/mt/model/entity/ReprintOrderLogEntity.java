package cn.com.ktm.mt.model.entity;

import java.math.BigDecimal;

public class ReprintOrderLogEntity {
    private Long id;

    private Long subOrderId;

    private Long orderId;

    private Long ticketId;

    private BigDecimal actualPrice;

    private Long inUserId;

    private String inName;

    private String inUserCardNo;

    private Integer inUserCertificateType;

    private String inUserCertificate;

    private Integer beginDate;

    private Integer endDate;

    private Integer beginTime;

    private Integer endTime;

    private Long sessionsId;

    private Long seatId;

    private String seatDetail;

    private Integer orderDetailStatus;

    private Integer payStatus;

    private Integer refundStatus;

    private String reason;

    private String auditRemark;

    private Integer inStatus;

    private Long inTime;

    private Integer isPrint;

    private Integer isVideoCupon;

    private Integer isVip;

    private String paperCode;

    private String beforePaperCode;

    private Integer isPrintTicket;

    private Long createUser;

    private Long createTime;

    private Long updateUser;

    private Long updateTime;

    private String ip;

    private Integer status;

    private Integer isDelete;

    private String remark;

    private Long deviceId;

    private String photopath;

    private Long refundTime;

    private Integer refundType;

    private Long refundUser;

    private String refundUserName;

    private Long venueId;

    private String subChannelOrderId;

    private Long channelId;

    private Integer isSendCheck;

    private Integer authVenue;

    private Integer finishVenue;

    private Long refundFinishTime;

    private Long printTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Long getInUserId() {
        return inUserId;
    }

    public void setInUserId(Long inUserId) {
        this.inUserId = inUserId;
    }

    public String getInName() {
        return inName;
    }

    public void setInName(String inName) {
        this.inName = inName == null ? null : inName.trim();
    }

    public String getInUserCardNo() {
        return inUserCardNo;
    }

    public void setInUserCardNo(String inUserCardNo) {
        this.inUserCardNo = inUserCardNo == null ? null : inUserCardNo.trim();
    }

    public Integer getInUserCertificateType() {
        return inUserCertificateType;
    }

    public void setInUserCertificateType(Integer inUserCertificateType) {
        this.inUserCertificateType = inUserCertificateType;
    }

    public String getInUserCertificate() {
        return inUserCertificate;
    }

    public void setInUserCertificate(String inUserCertificate) {
        this.inUserCertificate = inUserCertificate == null ? null : inUserCertificate.trim();
    }

    public Integer getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Integer beginDate) {
        this.beginDate = beginDate;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Long getSessionsId() {
        return sessionsId;
    }

    public void setSessionsId(Long sessionsId) {
        this.sessionsId = sessionsId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public String getSeatDetail() {
        return seatDetail;
    }

    public void setSeatDetail(String seatDetail) {
        this.seatDetail = seatDetail == null ? null : seatDetail.trim();
    }

    public Integer getOrderDetailStatus() {
        return orderDetailStatus;
    }

    public void setOrderDetailStatus(Integer orderDetailStatus) {
        this.orderDetailStatus = orderDetailStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }

    public Integer getInStatus() {
        return inStatus;
    }

    public void setInStatus(Integer inStatus) {
        this.inStatus = inStatus;
    }

    public Long getInTime() {
        return inTime;
    }

    public void setInTime(Long inTime) {
        this.inTime = inTime;
    }

    public Integer getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(Integer isPrint) {
        this.isPrint = isPrint;
    }

    public Integer getIsVideoCupon() {
        return isVideoCupon;
    }

    public void setIsVideoCupon(Integer isVideoCupon) {
        this.isVideoCupon = isVideoCupon;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode == null ? null : paperCode.trim();
    }

    public String getBeforePaperCode() {
        return beforePaperCode;
    }

    public void setBeforePaperCode(String beforePaperCode) {
        this.beforePaperCode = beforePaperCode == null ? null : beforePaperCode.trim();
    }

    public Integer getIsPrintTicket() {
        return isPrintTicket;
    }

    public void setIsPrintTicket(Integer isPrintTicket) {
        this.isPrintTicket = isPrintTicket;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath == null ? null : photopath.trim();
    }

    public Long getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Long refundTime) {
        this.refundTime = refundTime;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public Long getRefundUser() {
        return refundUser;
    }

    public void setRefundUser(Long refundUser) {
        this.refundUser = refundUser;
    }

    public String getRefundUserName() {
        return refundUserName;
    }

    public void setRefundUserName(String refundUserName) {
        this.refundUserName = refundUserName == null ? null : refundUserName.trim();
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public String getSubChannelOrderId() {
        return subChannelOrderId;
    }

    public void setSubChannelOrderId(String subChannelOrderId) {
        this.subChannelOrderId = subChannelOrderId == null ? null : subChannelOrderId.trim();
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Integer getIsSendCheck() {
        return isSendCheck;
    }

    public void setIsSendCheck(Integer isSendCheck) {
        this.isSendCheck = isSendCheck;
    }

    public Integer getAuthVenue() {
        return authVenue;
    }

    public void setAuthVenue(Integer authVenue) {
        this.authVenue = authVenue;
    }

    public Integer getFinishVenue() {
        return finishVenue;
    }

    public void setFinishVenue(Integer finishVenue) {
        this.finishVenue = finishVenue;
    }

    public Long getRefundFinishTime() {
        return refundFinishTime;
    }

    public void setRefundFinishTime(Long refundFinishTime) {
        this.refundFinishTime = refundFinishTime;
    }

    public Long getPrintTime() {
        return printTime;
    }

    public void setPrintTime(Long printTime) {
        this.printTime = printTime;
    }
}
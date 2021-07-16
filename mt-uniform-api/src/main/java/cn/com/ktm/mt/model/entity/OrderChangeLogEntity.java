package cn.com.ktm.mt.model.entity;

public class OrderChangeLogEntity {
    private Long id;

    private Long createUser;

    private Long createTime;

    private Integer status;

    private Long finishTime;

    private Integer initInDate;

    private Integer changeDate;

    private Long orderId;

    private String reqJson;

    private String   rescheduleNo;

    private String resJson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getInitInDate() {
        return initInDate;
    }

    public void setInitInDate(Integer initInDate) {
        this.initInDate = initInDate;
    }

    public Integer getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Integer changeDate) {
        this.changeDate = changeDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getReqJson() {
        return reqJson;
    }

    public void setReqJson(String reqJson) {
        this.reqJson = reqJson == null ? null : reqJson.trim();
    }

    public String getRescheduleNo() {
        return rescheduleNo;
    }

    public void setRescheduleNo(String rescheduleNo) {
        this.rescheduleNo = rescheduleNo;
    }

    public String getResJson() {
        return resJson;
    }

    public void setResJson(String resJson) {
        this.resJson = resJson;
    }
}
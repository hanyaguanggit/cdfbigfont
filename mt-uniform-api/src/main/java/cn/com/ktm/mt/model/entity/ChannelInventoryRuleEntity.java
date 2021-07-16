package cn.com.ktm.mt.model.entity;


public class ChannelInventoryRuleEntity {
    private Long id;

    private Long channelid;

    private Long venueid;

    private Integer daycount;

    private Long createuser;

    private Long createtime;

    private Long updateuser;

    private Long updatetime;

    private String ip;

    private Integer status;

    private Integer isdelete;

    private String remark;

    private String ticketids;

    private Integer limitDate;

    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChannelid() {
        return channelid;
    }

    public void setChannelid(Long channelid) {
        this.channelid = channelid;
    }

    public Long getVenueid() {
        return venueid;
    }

    public void setVenueid(Long venueid) {
        this.venueid = venueid;
    }

    public Integer getDaycount() {
        return daycount;
    }

    public void setDaycount(Integer daycount) {
        this.daycount = daycount;
    }

    public Long getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Long createuser) {
        this.createuser = createuser;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(Long updateuser) {
        this.updateuser = updateuser;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
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

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getTicketids() {
        return ticketids;
    }

    public void setTicketids(String ticketids) {
        this.ticketids = ticketids == null ? null : ticketids.trim();
    }


    public Integer getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Integer limitDate) {
        this.limitDate = limitDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
package cn.com.ktm.mt.model;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;

public class CsSurfaceSection {
    private Integer id;

    private Integer templateId;

    private Integer templateConfigId;

    private Integer siteStructureId;

    private Integer positionId;

    private Integer positionTypeId;

    private String name;

    private Integer advertiser;

    private Byte status;

    private Boolean target;

    private Date fromTime;

    private Date thruTime;

    private Short sequence;

    private Integer creator;

    private Date createTime;

    private Integer lastModifiedUser;

    private Date lastModifiedTime;

    private Boolean isPreload;

    private String virtualCategorys;

    private Integer shopId;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getTemplateConfigId() {
        return templateConfigId;
    }

    public void setTemplateConfigId(Integer templateConfigId) {
        this.templateConfigId = templateConfigId;
    }

    public Integer getSiteStructureId() {
        return siteStructureId;
    }

    public void setSiteStructureId(Integer siteStructureId) {
        this.siteStructureId = siteStructureId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getPositionTypeId() {
        return positionTypeId;
    }

    public void setPositionTypeId(Integer positionTypeId) {
        this.positionTypeId = positionTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(Integer advertiser) {
        this.advertiser = advertiser;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getTarget() {
        return target;
    }

    public void setTarget(Boolean target) {
        this.target = target;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getThruTime() {
        return thruTime;
    }

    public void setThruTime(Date thruTime) {
        this.thruTime = thruTime;
    }

    public Short getSequence() {
        return sequence;
    }

    public void setSequence(Short sequence) {
        this.sequence = sequence;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(Integer lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Boolean getIsPreload() {
        return isPreload;
    }

    public void setIsPreload(Boolean isPreload) {
        this.isPreload = isPreload;
    }

    public String getVirtualCategorys() {
        return virtualCategorys;
    }

    public void setVirtualCategorys(String virtualCategorys) {
        this.virtualCategorys = virtualCategorys == null ? null : virtualCategorys.trim();
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
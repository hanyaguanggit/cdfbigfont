package cn.com.ktm.mt.model;

import java.util.Date;

public class CsSubsite {
    private Integer id;

    private String subSiteName;

    private String subSiteNameEN;

    private String code;

    private String url;

    private String SLD;

    private Byte status;

    private Date createTime;

    private Integer creator;

    private Date lastModifiedTime;

    private Integer lastModifiedUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubSiteName() {
        return subSiteName;
    }

    public void setSubSiteName(String subSiteName) {
        this.subSiteName = subSiteName == null ? null : subSiteName.trim();
    }

    public String getSubSiteNameEN() {
        return subSiteNameEN;
    }

    public void setSubSiteNameEN(String subSiteNameEN) {
        this.subSiteNameEN = subSiteNameEN == null ? null : subSiteNameEN.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getSLD() {
        return SLD;
    }

    public void setSLD(String SLD) {
        this.SLD = SLD == null ? null : SLD.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Integer getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(Integer lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }
}
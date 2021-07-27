package cn.com.ktm.mt.model.bean;

import java.util.Date;

public class CsArticle {
    private Integer id;

    private Integer categoryId;

    private String name;

    private String nameEN;

    private String authorName;

    private String authorNameEN;

    private Date publishTime;

    private String info;

    private String infoEN;

    private Byte status;

    private Short sequence;

    private Integer visitCount;

    private Date createTime;

    private Integer creator;

    private Date lastModifiedTime;

    private Integer lastModifiedUser;

    private Integer picId;

    private Integer shopId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN == null ? null : nameEN.trim();
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
    }

    public String getAuthorNameEN() {
        return authorNameEN;
    }

    public void setAuthorNameEN(String authorNameEN) {
        this.authorNameEN = authorNameEN == null ? null : authorNameEN.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getInfoEN() {
        return infoEN;
    }

    public void setInfoEN(String infoEN) {
        this.infoEN = infoEN == null ? null : infoEN.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Short getSequence() {
        return sequence;
    }

    public void setSequence(Short sequence) {
        this.sequence = sequence;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
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

    public Integer getPicId() {
        return picId;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}
package cn.com.ktm.mt.model;

public class CsSurfaceSectionTemplateConfig {
    private Integer id;

    private Integer templateId;

    private Integer sitestructureId;

    private Integer positionId;

    private Integer positiontypeId;

    private String linkTabname;

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

    public Integer getSitestructureId() {
        return sitestructureId;
    }

    public void setSitestructureId(Integer sitestructureId) {
        this.sitestructureId = sitestructureId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getPositiontypeId() {
        return positiontypeId;
    }

    public void setPositiontypeId(Integer positiontypeId) {
        this.positiontypeId = positiontypeId;
    }

    public String getLinkTabname() {
        return linkTabname;
    }

    public void setLinkTabname(String linkTabname) {
        this.linkTabname = linkTabname == null ? null : linkTabname.trim();
    }
}
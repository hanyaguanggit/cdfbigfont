package cn.com.ktm.mt.model;


public class CsStructurePosition {

    private Integer id;

    private String name;

    private Integer positiontypeId;

    private Integer siteStructureId;

    private String remark;

    private Byte eternal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPositiontypeId() {
        return positiontypeId;
    }

    public void setPositiontypeId(Integer positiontypeId) {
        this.positiontypeId = positiontypeId;
    }

    public Integer getSiteStructureId() {
        return siteStructureId;
    }

    public void setSiteStructureId(Integer siteStructureId) {
        this.siteStructureId = siteStructureId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getEternal() {
        return eternal;
    }

    public void setEternal(Byte eternal) {
        this.eternal = eternal;
    }
}
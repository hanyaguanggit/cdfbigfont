package cn.com.ktm.mt.model;

public class CsSurfaceTextLink {
    private Integer id;

    private Integer sectionId;

    private String name;

    private String nameEN;

    private String url;

    private String urlEN;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUrlEN() {
        return urlEN;
    }

    public void setUrlEN(String urlEN) {
        this.urlEN = urlEN == null ? null : urlEN.trim();
    }
}
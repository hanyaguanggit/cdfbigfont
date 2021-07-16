package cn.com.ktm.mt.model;

public class CsSurfacePictureLink {
    private Integer id;

    private Integer sectionId;

    private Integer picture;

    private Integer pictureEN;

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

    public Integer getPicture() {
        return picture;
    }

    public void setPicture(Integer picture) {
        this.picture = picture;
    }

    public Integer getPictureEN() {
        return pictureEN;
    }

    public void setPictureEN(Integer pictureEN) {
        this.pictureEN = pictureEN;
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
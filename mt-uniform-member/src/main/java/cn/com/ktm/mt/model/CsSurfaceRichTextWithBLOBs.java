package cn.com.ktm.mt.model;

public class CsSurfaceRichTextWithBLOBs extends CsSurfaceRichText {
    private String content;

    private String contentEN;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getContentEN() {
        return contentEN;
    }

    public void setContentEN(String contentEN) {
        this.contentEN = contentEN == null ? null : contentEN.trim();
    }
}
package cn.com.ktm.mt.model;

public class CsProductWithBLOBs extends CsProduct {
    private String details;

    private String detailsEN;

    private String mobileDetails;

    private String mobileDetailsEN;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    public String getDetailsEN() {
        return detailsEN;
    }

    public void setDetailsEN(String detailsEN) {
        this.detailsEN = detailsEN == null ? null : detailsEN.trim();
    }

    public String getMobileDetails() {
        return mobileDetails;
    }

    public void setMobileDetails(String mobileDetails) {
        this.mobileDetails = mobileDetails == null ? null : mobileDetails.trim();
    }

    public String getMobileDetailsEN() {
        return mobileDetailsEN;
    }

    public void setMobileDetailsEN(String mobileDetailsEN) {
        this.mobileDetailsEN = mobileDetailsEN == null ? null : mobileDetailsEN.trim();
    }
}
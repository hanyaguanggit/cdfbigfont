package cn.com.ktm.mt.model.entity;

import cn.com.ktm.mt.model.message.Body;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通知请求
 */
public class PartnerChannelEntity implements Body {
    private int tenantId = 28147;
    private String tenantName = "西安博物院";

    public PartnerChannelEntity(String partnerId, int channelId, String firstValidTime, int isValid, String lastUpdateTime) {
        this.partnerId = partnerId;
        this.channelId = channelId;
        this.firstValidTime = firstValidTime;
        this.isValid = isValid;
        this.lastUpdateTime = lastUpdateTime;
        this.tenantId= Integer.valueOf(partnerId);
    }

    private String partnerId;
    private int channelId;
    private String firstValidTime ;
    private int isValid;

    public PartnerChannelEntity(String partnerId, int channelId, int isValid) {
        this.partnerId = partnerId;
        this.channelId = channelId;
        this.isValid = isValid;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.lastUpdateTime =sf.format(new Date());
        this.firstValidTime ="2020-01-10 10:10:11";
        this.tenantId= Integer.valueOf(partnerId);
    }

    private String lastUpdateTime;

    public PartnerChannelEntity() {
    }

    public PartnerChannelEntity(int tenantId, String tenantName, String partnerId, int channelId, String firstValidTime, int isValid, String lastUpdateTime) {
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.partnerId = partnerId;
        this.channelId = channelId;
        this.firstValidTime = firstValidTime;
        this.isValid = isValid;
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getfirstValidTime() {
        return firstValidTime;
    }

    public void setfirstValidTime(String firstValidTime) {
        this.firstValidTime = firstValidTime;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public void valid() {
        // TODO Auto-generated method stub
    }
}

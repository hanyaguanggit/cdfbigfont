package cn.com.ktm.mt.model.bean;

import java.util.Date;

public class CsMember {
    private Integer id;

    private String loginName;

    private String email;

    private String mobile;

    private String loginPassword;

    private String payPassword;

    private Integer memberLevelId;

    private Byte regSource;

    private Byte entry;

    private Integer regSubsiteId;

    private Byte regLanguageType;

    private String regIP;

    private Date regTime;

    private String c4MemberCardNo;

    private Boolean c4MemberCardNoValidated;

    private Date c4MemberCardNoValidatedTime;

    private Boolean mobileValidated;

    private Date mobileValidatedTime;

    private Boolean mailValidated;

    private Date mailvalidatedTime;

    private Byte status;

    private Date pointPayLockTime;

    private Byte loginPasswordStrength;

    private Byte payPasswordStrength;

    private Integer unionId;

    private Integer unionMemberId;

    private Byte multipleChannelsId;

    private String salt;

    private Integer oldMemberId;

    private String enterNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    public Integer getMemberLevelId() {
        return memberLevelId;
    }

    public void setMemberLevelId(Integer memberLevelId) {
        this.memberLevelId = memberLevelId;
    }

    public Byte getRegSource() {
        return regSource;
    }

    public void setRegSource(Byte regSource) {
        this.regSource = regSource;
    }

    public Byte getEntry() {
        return entry;
    }

    public void setEntry(Byte entry) {
        this.entry = entry;
    }

    public Integer getRegSubsiteId() {
        return regSubsiteId;
    }

    public void setRegSubsiteId(Integer regSubsiteId) {
        this.regSubsiteId = regSubsiteId;
    }

    public Byte getRegLanguageType() {
        return regLanguageType;
    }

    public void setRegLanguageType(Byte regLanguageType) {
        this.regLanguageType = regLanguageType;
    }

    public String getRegIP() {
        return regIP;
    }

    public void setRegIP(String regIP) {
        this.regIP = regIP == null ? null : regIP.trim();
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getC4MemberCardNo() {
        return c4MemberCardNo;
    }

    public void setC4MemberCardNo(String c4MemberCardNo) {
        this.c4MemberCardNo = c4MemberCardNo == null ? null : c4MemberCardNo.trim();
    }

    public Boolean getC4MemberCardNoValidated() {
        return c4MemberCardNoValidated;
    }

    public void setC4MemberCardNoValidated(Boolean c4MemberCardNoValidated) {
        this.c4MemberCardNoValidated = c4MemberCardNoValidated;
    }

    public Date getC4MemberCardNoValidatedTime() {
        return c4MemberCardNoValidatedTime;
    }

    public void setC4MemberCardNoValidatedTime(Date c4MemberCardNoValidatedTime) {
        this.c4MemberCardNoValidatedTime = c4MemberCardNoValidatedTime;
    }

    public Boolean getMobileValidated() {
        return mobileValidated;
    }

    public void setMobileValidated(Boolean mobileValidated) {
        this.mobileValidated = mobileValidated;
    }

    public Date getMobileValidatedTime() {
        return mobileValidatedTime;
    }

    public void setMobileValidatedTime(Date mobileValidatedTime) {
        this.mobileValidatedTime = mobileValidatedTime;
    }

    public Boolean getMailValidated() {
        return mailValidated;
    }

    public void setMailValidated(Boolean mailValidated) {
        this.mailValidated = mailValidated;
    }

    public Date getMailvalidatedTime() {
        return mailvalidatedTime;
    }

    public void setMailvalidatedTime(Date mailvalidatedTime) {
        this.mailvalidatedTime = mailvalidatedTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getPointPayLockTime() {
        return pointPayLockTime;
    }

    public void setPointPayLockTime(Date pointPayLockTime) {
        this.pointPayLockTime = pointPayLockTime;
    }

    public Byte getLoginPasswordStrength() {
        return loginPasswordStrength;
    }

    public void setLoginPasswordStrength(Byte loginPasswordStrength) {
        this.loginPasswordStrength = loginPasswordStrength;
    }

    public Byte getPayPasswordStrength() {
        return payPasswordStrength;
    }

    public void setPayPasswordStrength(Byte payPasswordStrength) {
        this.payPasswordStrength = payPasswordStrength;
    }

    public Integer getUnionId() {
        return unionId;
    }

    public void setUnionId(Integer unionId) {
        this.unionId = unionId;
    }

    public Integer getUnionMemberId() {
        return unionMemberId;
    }

    public void setUnionMemberId(Integer unionMemberId) {
        this.unionMemberId = unionMemberId;
    }

    public Byte getMultipleChannelsId() {
        return multipleChannelsId;
    }

    public void setMultipleChannelsId(Byte multipleChannelsId) {
        this.multipleChannelsId = multipleChannelsId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Integer getOldMemberId() {
        return oldMemberId;
    }

    public void setOldMemberId(Integer oldMemberId) {
        this.oldMemberId = oldMemberId;
    }

    public String getEnterNo() {
        return enterNo;
    }

    public void setEnterNo(String enterNo) {
        this.enterNo = enterNo == null ? null : enterNo.trim();
    }
}
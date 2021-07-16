package cn.com.ktm.mt.model.message.member.groupverifynotice.request;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.entity.OrganizationEntity;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupVerifyNotifyRequestBody implements Body {

    private Integer userType;//⽤户类型 1=散客 2=旅⾏社 3=特殊团队
    private String phone;//散客⼿机号
    private String userId;//系统商⽤户ID
    private String orgCode;//组织机构代码
    private Integer auditStatus ;// 审核状态（通过/不通过/审核中） 0未审核，1已通过，2已拒绝
    private Long auditStatusChangedTime;//通知时间戳
    private Integer status;//状态(可⽤/不可⽤）审核状态通过的时候 默认可⽤，在后续可能将状态进⾏扭转让 其不可再次购票的时候回改变该状态 1可⽤ 0 不可

    @Override
    public void valid() {
        Assert.notNull(userType, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"用户类型为空");
        Assert.notBlank(phone, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"用户手机号为空");
        Assert.notBlank(userId, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"系统商⽤户ID为空");
        Assert.notBlank(orgCode, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"组织机构代码为空");
        Assert.notNull(auditStatus, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"审核状态为空");
        Assert.notNull(auditStatusChangedTime, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"通知时间戳为空");
        Assert.notNull(status, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"状态为空");

    }
    public OrganizationEntity transform(GroupVerifyNotifyRequest request){
        OrganizationEntity organization = new OrganizationEntity();
        organization.setOrgCode(request.getBody().getOrgCode());
        organization.setAuditStatus(request.getBody().getAuditStatus());
        organization.setVerifiedTime(request.getBody().getAuditStatusChangedTime());
        organization.setStatus(request.getBody().getStatus());
        return organization;
    }
}

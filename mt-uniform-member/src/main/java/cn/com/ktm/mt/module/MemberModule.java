package cn.com.ktm.mt.module;

import cn.com.ktm.mt.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberModule {
    Logger logger = LoggerFactory.getLogger(MemberModule.class);

 /*   @Autowired
    private UserService UserService;*/

    @Autowired
    private OrganizationService organizationService;
    


  /*  public PersonalLoginResponse personalLogin(PersonalLoginRequest request) {
        Assert.notBlank(request.getPartnerId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "商家ID为空");
        Assert.notNull(request.getChannelId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "渠道来源为空");
        request.getBody().valid();

        PartnerEntity partner = partnerApi.findPartner(request.getPartnerId());
        Assert.notNull(partner,ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "商家ID错误");
        Assert.isTrue(request.getChannelId() > 0 && request.getChannelId() <= MTChannelEnum.values().length, ResponseConsts.MEMBER_PARAM_ERROR,"渠道来源错误");
        Assert.isTrue(ValidUtil.isMobile(request.getBody().getPhone()), ResponseConsts.MEMBER_PHONE_ERROR, "手机号输入错误");
        Assert.isTrue(request.getBody().getUserType() == MTUserTypeEnum.PERSONAL.getValue(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "用户类型错误");
        if (!request.getBody().getValidCode().equalsIgnoreCase(RedisCache.db().get(request.getBody().getValidToken()))) {
            Assert.fail(ResponseConsts.MEMBER_VALIDCODE_ERROR, "图形验证码校验失败");
        }
        RedisCache.db().del(request.getBody().getValidToken());

        //Assert.eq(request.getBody().getSmsCode(),RedisCache.db().get(MTSourceEnum.PERSONAL_REGISTER.getValue()+request.getBody().getPhone()),ResponseConsts.MEMBER_SMSCODE_ERROR,"短信验证码相关验证失败");
        Assert.eq(request.getBody().getSmsCode(),RedisCache.db().get(MTSourceEnum.PERSONAL_REGISTER.getValue()+request.getBody().getPhone()),ResponseConsts.MEMBER_SMSCODE_ERROR,"短信验证码相关验证失败");
        RedisCache.db().del(MTSourceEnum.PERSONAL_REGISTER.getValue()+request.getBody().getPhone());

        PersonalLoginResponse response = new PersonalLoginResponse();
        PersonalLoginResponseBody responseBody = new PersonalLoginResponseBody();


        try {
            UserEntity userEntity = UserService.findUserByPhone(request.getBody().getPhone());
            if (ObjectUtils.allNotNull(userEntity)) {

                responseBody.setUserId(String.valueOf(userEntity.getId()));
                responseBody.setPhone(userEntity.getPhone());
                responseBody.setUserType(userEntity.getUserType());

                response.setBody(responseBody);

            } else {
                UserEntity entity = request.getBody().transform(request);
                UserEntity userEntity1 = UserService.personalLogin(entity);
                responseBody.setUserId(String.valueOf(userEntity1.getId()));
                responseBody.setPhone(userEntity1.getPhone());
                responseBody.setUserType(userEntity1.getUserType());

                response.setBody(responseBody);

            }
            response.setCode(ResponseConsts.SUCCESS);
            response.setDescribe("success");
            response.setPartnerId(request.getPartnerId());
            response.setChannelId(request.getChannelId());

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION);

        }

        return response;
    }*/

    /**
     * 团队注册
     * @param request
     * @return
     */
   /* public GroupRegisterResponse groupRegister(GroupRegisterRequest request) {
        Assert.notBlank(request.getPartnerId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "商家ID为空");
        Assert.notNull(request.getChannelId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "渠道来源为空");
        request.getBody().valid();
        PartnerEntity partner = partnerApi.findPartner(request.getPartnerId());
        Assert.notNull(partner,ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "商家ID错误");
        Assert.isTrue(request.getChannelId() > 0 && request.getChannelId() <= MTChannelEnum.values().length, ResponseConsts.MEMBER_PARAM_ERROR,"渠道来源错误");
        Assert.isTrue(ValidUtil.uniformSocialCreditCodeValidate(request.getBody().getOrgCode()), ResponseConsts.MEMBER_AUDIT_INFORMATION_ERROR, "组织机构代码错误");
        Assert.isTrue(ValidUtil.isMobile(request.getBody().getPhone()), ResponseConsts.MEMBER_PHONE_ERROR);
        Assert.isTrue(request.getBody().getUserType() == MTUserTypeEnum.TOUR_GROUP.getValue() || request.getBody().getUserType() == MTUserTypeEnum.SPECIAL_GROUP.getValue(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "⽤户类型错误");
        Assert.isTrue(ValidUtil.isRightPwd(request.getBody().getPassword()),ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"密码必须由6至16位数字和字母组成");
        if (!request.getBody().getValidCode().equalsIgnoreCase(RedisCache.db().get(request.getBody().getValidToken()))) {
            Assert.fail(ResponseConsts.MEMBER_VALIDCODE_ERROR, "图形验证码校验失败");
        }
       if (request.getBody().getUserType() == MTUserTypeEnum.TOUR_GROUP.getValue()) {
            Assert.isTrue(ValidUtil.checkEmail(request.getBody().getEmail()), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "邮箱格式错误");
        }
        //Assert.eq(request.getBody().getSmsCode(),RedisCache.db().get(MTSourceEnum.PERSONAL_REGISTER.getValue()+request.getBody().getPhone()),ResponseConsts.MEMBER_SMSCODE_ERROR,"短信验证码相关验证失败");
        Assert.eq(request.getBody().getSmsCode(),RedisCache.db().get(MTSourceEnum.GROUP_REGISTER.getValue()+request.getBody().getPhone()),ResponseConsts.MEMBER_SMSCODE_ERROR,"短信验证码相关验证失败");
        RedisCache.db().del(MTSourceEnum.GROUP_REGISTER.getValue()+request.getBody().getPhone());

        Assert.isNull(organizationService.findGroupByOrgCode(request.getBody().getOrgCode()), ResponseConsts.MEMBER_ACCOUNT_IS_EXIST, "账号已被注册");

        OrganizationEntity entity = request.getBody().transform(request);
        GroupRegisterResponse response = new GroupRegisterResponse();
        GroupRegisterResponseBody responseBody = new GroupRegisterResponseBody();

        try {
            OrganizationEntity organization = organizationService.groupRegister(entity);

            responseBody.setAuditStatus(organization.getAuditStatus());
            responseBody.setUserType(organization.getType());
            responseBody.setUserId(String.valueOf(organization.getId()));
            responseBody.setOrgCode(organization.getOrgCode());
            responseBody.setStatus(organization.getStatus());
            response.setBody(responseBody);
            response.setCode(ResponseConsts.SUCCESS);
            response.setDescribe("success");
            response.setPartnerId(request.getPartnerId());
            response.setChannelId(request.getChannelId());

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION);
        }

        return response;
    }*/

/*    public GroupLoginResponse groupLogin(GroupLoginRequest request) {
        Assert.notNull(request.getChannelId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, " 渠道来源为空");
        Assert.notBlank(request.getPartnerId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "商家ID为空");
        request.getBody().valid();

        PartnerEntity partner = partnerApi.findPartner(request.getPartnerId());
        Assert.notNull(partner,ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "商家ID错误");
        Assert.isTrue(request.getChannelId() > 0 && request.getChannelId() <= MTChannelEnum.values().length, ResponseConsts.MEMBER_PARAM_ERROR,"渠道来源错误");
        Assert.isTrue(ValidUtil.uniformSocialCreditCodeValidate(request.getBody().getOrgCode()), ResponseConsts.MEMBER_AUDIT_INFORMATION_ERROR, "组织机构代码错误");
        Assert.isTrue(request.getBody().getUserType() == MTUserTypeEnum.TOUR_GROUP.getValue() || request.getBody().getUserType() == MTUserTypeEnum.SPECIAL_GROUP.getValue(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "⽤户类型错误");
        if (!request.getBody().getValidCode().equalsIgnoreCase(RedisCache.db().get(request.getBody().getValidToken()))) {
            Assert.fail(ResponseConsts.MEMBER_VALIDCODE_ERROR, "图形验证码校验失败");
        }
        RedisCache.db().del(request.getBody().getValidToken());
        GroupLoginResponse response = new GroupLoginResponse();
        GroupLoginResponseBody responseBody = new GroupLoginResponseBody();

        OrganizationEntity organization = null;
        try {
            organization = organizationService.findGroupByOrgCodeAndUserType(request.getBody().getOrgCode(), request.getBody().getUserType());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION);
        }
        if (!ObjectUtils.allNotNull(organization)) {
            Assert.fail(ResponseConsts.MEMBER_ACCOUNT_PASSWORD_ERROR, "团队登录账号或密码错误");
        } else {
            Assert.eq(organization.getPassword(), MD5Util.strToMD5(request.getBody().getPassword()), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "团队登录账号或密码错误", null);

        }
        responseBody.setUserId(String.valueOf(organization.getId()));
        responseBody.setUserType(organization.getType());
        responseBody.setOrgCode(organization.getOrgCode());
        response.setBody(responseBody);
        response.setCode(ResponseConsts.SUCCESS);
        response.setDescribe("success");
        response.setPartnerId(request.getPartnerId());
        response.setChannelId(request.getChannelId());


        return response;
    }

    public OtaResponse getUserInfo(UserInfoRequest request) {
        Assert.notNull(request.getChannelId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, " 渠道来源为空");
        Assert.notBlank(request.getPartnerId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "商家ID为空");
        request.getBody().valid();
        PartnerEntity partner = partnerApi.findPartner(request.getPartnerId());
        Assert.notNull(partner,ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "商家ID错误");
        Assert.isTrue(request.getChannelId() > 0 && request.getChannelId() <= MTChannelEnum.values().length, ResponseConsts.MEMBER_PARAM_ERROR,"渠道来源错误");
        Assert.isTrue(request.getBody().getUserType() > 0 && request.getBody().getUserType() <= MTUserTypeEnum.values().length, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "⽤户类型错误");
        if (request.getBody().getUserType() == MTUserTypeEnum.PERSONAL.getValue()) {
            Assert.isTrue(ValidUtil.isMobile(request.getBody().getPhone()), ResponseConsts.MEMBER_PARAM_ERROR, "手机号输入错误");
        }
        if (request.getBody().getUserType() == MTUserTypeEnum.TOUR_GROUP.getValue() || request.getBody().getUserType() == MTUserTypeEnum.SPECIAL_GROUP.getValue()) {
            Assert.isTrue(ValidUtil.uniformSocialCreditCodeValidate(request.getBody().getOrgCode()), ResponseConsts.MEMBER_PARAM_ERROR, "组织机构代码输入有误");
        }

        UserInfoResponse response = new UserInfoResponse();
        UserInfoResponseBody responseBody = new UserInfoResponseBody();

        if (request.getBody().getUserType() == MTUserTypeEnum.PERSONAL.getValue()) {
            UserEntity user = UserService.getUserInfo(request.getBody().getPhone(), request.getBody().getUserId());
            Assert.notNull(user, ResponseConsts.MEMBER_USERINFO_NOT_EXIST, "找不到相应⽤户信息");
            if (ObjectUtils.allNotNull(user.getUname())) {
                responseBody.setContactName(user.getUname());
            }
            responseBody.setPhone(user.getPhone());

            if (ObjectUtils.allNotNull(user.getEmail())) {
                responseBody.setEmail(user.getEmail());
            }
            response.setBody(responseBody);
        }
        if (request.getBody().getUserType() == MTUserTypeEnum.TOUR_GROUP.getValue() || request.getBody().getUserType() == MTUserTypeEnum.SPECIAL_GROUP.getValue()) {

            OrganizationEntity organization = organizationService.getOrganization(request.getBody().getOrgCode(), request.getBody().getUserType(), request.getBody().getUserId());
            Assert.notNull(organization, ResponseConsts.MEMBER_USERINFO_NOT_EXIST, "找不到相应⽤户信息");
            responseBody.setContactName(organization.getContactName());
            responseBody.setPhone(organization.getPhone());
            responseBody.setOrgName(organization.getRegName());
            if (ObjectUtils.allNotNull(organization.getEmail())) {
                responseBody.setEmail(organization.getEmail());
            }
            responseBody.setOrgCode(organization.getOrgCode());
            responseBody.setAuditStatus(organization.getAuditStatus());
            responseBody.setStatus(organization.getStatus());
            Map<String, List<String>> submitAuditContent = new HashMap<>();

            if (organization.getOrgCodeFile() != null && organization.getOrgCodeFile() != "") {
                submitAuditContent.put("orgCodeFiles", Collections.singletonList(organization.getOrgCodeFile()));
            }
            if (organization.getLicenseFile() != null && organization.getLicenseFile() != "") {
                submitAuditContent.put("licenseFiles", Collections.singletonList(organization.getLicenseFile()));
            }
            if (organization.getPolicyFiles() != null && organization.getPolicyFiles() != "") {
                submitAuditContent.put("policyFiles", Arrays.asList(organization.getPolicyFiles().split(MemberConsts.SEPARATOR)));
            }
            if (organization.getSubOrgFiles() != null && organization.getSubOrgFiles() != "") {
                submitAuditContent.put("subOrgFiles", Arrays.asList(organization.getSubOrgFiles().split(MemberConsts.SEPARATOR)));
            }
            responseBody.setSubmitAuditContent(submitAuditContent);
            response.setBody(responseBody);
        }
        response.setCode(ResponseConsts.SUCCESS);
        response.setDescribe("success");
        response.setPartnerId(request.getPartnerId());
        response.setChannelId(request.getChannelId());


        return response;
    }

    *//**
     * 1.6 忘记密码(团体重置密码请求)
     * @param request
     * @return
     *//*
    public OtaResponse resetPassword(ResetPasswordRequest request) {
        Assert.notBlank(request.getPartnerId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "商家ID为空");
        Assert.notNull(request.getChannelId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "渠道来源为空");
        request.getBody().valid();
        PartnerEntity partner = partnerApi.findPartner(request.getPartnerId());
        Assert.notNull(partner,ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "商家ID错误");
        Assert.isTrue(request.getChannelId() > 0 && request.getChannelId() <= MTChannelEnum.values().length, ResponseConsts.MEMBER_PARAM_ERROR,"渠道来源错误");
        Assert.isTrue(request.getBody().getUserType() == MTUserTypeEnum.TOUR_GROUP.getValue() || request.getBody().getUserType() == MTUserTypeEnum.SPECIAL_GROUP.getValue(), ResponseConsts.MEMBER_PARAM_ERROR, "⽤户类型错误");
        Assert.isTrue(ValidUtil.isRightPwd(request.getBody().getPassword()),ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"密码必须由6至11位特殊字符、数字、大写字母和小写字母组成");
        Assert.isTrue(ValidUtil.uniformSocialCreditCodeValidate(request.getBody().getOrgCode()), ResponseConsts.MEMBER_PARAM_ERROR, "组织机构代码输入有误");
        if (!request.getBody().getValidCode().equalsIgnoreCase(RedisCache.db().get(request.getBody().getValidToken()))) {
            Assert.fail(ResponseConsts.MEMBER_VALIDCODE_ERROR, "图形验证码校验失败");
        }


        OrganizationEntity organization = organizationService.findGroupByOrgCodeAndUserType(request.getBody().getOrgCode(), request.getBody().getUserType());

        Assert.notNull(organization, ResponseConsts.MEMBER_USERINFO_NOT_EXIST, "⽆效⽤户");
        String redisKey="forget_password";//团队注册
        Assert.eq(request.getBody().getSmsCode(),RedisCache.db().get(MTSourceEnum.RESET_PASSWORD.getValue()+organization.getPhone()),ResponseConsts.MEMBER_SMSCODE_ERROR,"短信验证码相关验证失败");
        RedisCache.db().del(MTSourceEnum.RESET_PASSWORD.getValue()+organization.getPhone());
        ResetPasswordResponse response = new ResetPasswordResponse();
        ResetPasswordResponseBody responseBody = new ResetPasswordResponseBody();

        try {
            organization.setPassword(MD5Util.strToMD5(request.getBody().getPassword()));

            organizationService.updateOrganizationById(organization);

            responseBody.setOrgCode(organization.getOrgCode());
            responseBody.setUserType(organization.getType());
            response.setBody(responseBody);
            response.setCode(ResponseConsts.SUCCESS);
            response.setDescribe("success");
            response.setPartnerId(request.getPartnerId());
            response.setChannelId(request.getChannelId());

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION);
        }
        return response;
    }

    public OrganizationEntity findGroupInfo(String userId) {
       return organizationService.findGroupById(userId);

    }*/


}

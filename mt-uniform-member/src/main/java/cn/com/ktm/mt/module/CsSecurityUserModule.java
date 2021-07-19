package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.CsSecurityUser;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.message.member.personallogin.response.PersonalLoginResponse;
import cn.com.ktm.mt.model.message.member.resetpassword.request.ResetPasswordRequest;
import cn.com.ktm.mt.model.message.member.resetpassword.response.ResetPasswordResponse;
import cn.com.ktm.mt.model.message.member.resetpassword.response.ResetPasswordResponseBody;
import cn.com.ktm.mt.model.redis.RedisCache;
import cn.com.ktm.mt.model.security.request.LoginRequestVo;
import cn.com.ktm.mt.model.security.response.LoginResponse;
import cn.com.ktm.mt.model.security.response.LoginResponseBody;
import cn.com.ktm.mt.model.util.utils.ValidUtil;
import cn.com.ktm.mt.model.util.utils.security.AesUtils;
import cn.com.ktm.mt.service.CsSecurityUserService;
import cn.com.ktm.mt.service.SecurityMenuService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * hyg 2021.07.16
 * 后台管理员相关
 */
@Component
public class CsSecurityUserModule {
    Logger logger = LoggerFactory.getLogger(CsSecurityUserModule.class);

    @Autowired
    private CsSecurityUserService csSecurityUserService;

    @Autowired
    private SecurityMenuService securityMenuService;

    /**
     * 后台登录
     * @param request
     * @return
     */
    public LoginResponse adminLogin(LoginRequestVo request) {
       /* Assert.notBlank(request.getPartnerId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "商家ID为空");
        Assert.notNull(request.getChannelId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "渠道来源为空");*/
        request.getBody().valid();
        if (!request.getBody().getValidCode().equalsIgnoreCase(RedisCache.db().get(request.getBody().getValidCode()))) {
           // Assert.fail(ResponseConsts.MEMBER_VALIDCODE_ERROR, "图形验证码校验失败");
        }
        //RedisCache.db().del(request.getBody().getValidCode());

        //先通过用户名和密码查询到用户id，再查询其拥有的菜单权限。
        LoginResponse response = new LoginResponse();
        try {
            String pass = AesUtils.encrypt("",request.getBody().getPassword());
            logger.info("解密的字符串:{}",pass);
            CsSecurityUser csSecurityUser = csSecurityUserService.selectByLoginNameAndPassword(request.getBody().getUserName(),pass);
            if(csSecurityUser != null){
                //TODO：是否验证后台用户失效日期
                if(csSecurityUser.getLocked()){
                    response.setDescribe("此用户已被锁定，请联系管理员。");
                }
                //登录成功
                response.setCsSecurityUser(csSecurityUser);
                //获取权限菜单
                List<LoginResponseBody> menuList = securityMenuService.findMenuByUserId(csSecurityUser.getId());
                if(menuList != null && menuList.size() > 0){
                    logger.info("所拥有的菜单:{}",menuList);
                    response.setMenuList(menuList);
                    response.setCode(ResponseConsts.SUCCESS);
                    response.setDescribe("登录成功。");
                }else{
                    response.setCode(ResponseConsts.SUCCESS);
                    response.setDescribe("此人无任何权限！");
                }
            }else{
                response.setCode(ResponseConsts.SUCCESS);
                response.setDescribe("查无此人!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION);
        }
        return response;
    }


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

    /**
     * 1.6 忘记密码(重置密码请求)
     * @param request
     * @return
     */
    public ResetPasswordResponse resetPassword(ResetPasswordRequest request) {
       /* Assert.notBlank(request.getPartnerId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "商家ID为空");
        Assert.notNull(request.getChannelId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "渠道来源为空");*/
        request.getBody().valid();
        //Assert.isTrue(ValidUtil.isRightPwd(request.getBody().getOldPassword()),ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"当前密码必须由6至11位特殊字符、数字、大写字母和小写字母组成");
        Assert.isTrue(ValidUtil.isRightPwd(request.getBody().getNewPassword()),ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"新密码必须由6至11位特殊字符、数字、大写字母和小写字母组成");
        ResetPasswordResponse response = new ResetPasswordResponse();
        ResetPasswordResponseBody responseBody = new ResetPasswordResponseBody();
        String oldpass="",newpass="" ;
        try {
            oldpass = AesUtils.encrypt("", request.getBody().getOldPassword());
            newpass = AesUtils.encrypt("", request.getBody().getNewPassword());
            logger.info("旧密码加密后的字符串:{}",oldpass);
            logger.info("新密码加密后的字符串:{}",newpass);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(ResponseConsts.ERROR,"加密异常。");
        }
        CsSecurityUser csSecurityUser = csSecurityUserService.selectByUserIdAndPassword(request.getBody().getUserId(), oldpass);
        Assert.notNull(csSecurityUser, ResponseConsts.MEMBER_USERINFO_NOT_EXIST, "⽆效⽤户");
        csSecurityUser.setPassword(newpass);
        csSecurityUser.setLastModifiedUser(request.getBody().getCreateUserId());
        csSecurityUser.setLastModifiedTime(new Date());
        if(csSecurityUserService.updateByPrimaryKeySelective(csSecurityUser) > 0){
            responseBody.setUserId(csSecurityUser.getId());
            response.setBody(responseBody);
            response.setCode(ResponseConsts.SUCCESS);
            response.setDescribe("修改密码成功。");
        }else{
            response.setCode(ResponseConsts.ERROR);
            response.setDescribe("修改密码失败,请联系管理员。");
        }
        return response;
    }



}

package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.bean.SmsBean;
import cn.com.ktm.mt.model.constant.RedisKey;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.entity.OrganizationEntity;
import cn.com.ktm.mt.model.entity.PartnerEntity;
import cn.com.ktm.mt.model.entity.SmsTempletEntity;
import cn.com.ktm.mt.model.enums.SmsKeyEnum;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.message.member.sendsmscode.response.SendsmsCodeResponse;
import cn.com.ktm.mt.model.redis.RedisCache;
import cn.com.ktm.mt.model.redis.RedisQueue;
import cn.com.ktm.mt.model.util.utils.JsonUtil;
import cn.com.ktm.mt.model.util.utils.ValidUtil;
import cn.com.ktm.mt.service.OrganizationService;
import cn.com.ktm.mt.service.SmsSendService;
import cn.com.ktm.mt.service.SmsTemplateService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SmsCodeModule {
    @Autowired
    private OrganizationService organizationService;
  /*  @Autowired
    private PartnerApi partnerApi;*/
    @Autowired
    private SmsSendService smsSendService;
    @Autowired
    private SmsTemplateService smsTemplateService;


    /*public OtaResponse sendSmsCode(SendSmsVeriﬁcationCodeRequest request) {
        final String flag = "_flag";

        Assert.notBlank(request.getPartnerId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "商家ID为空");
        Assert.notNull(request.getChannelId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "渠道来源为空");
        request.getBody().valid();
        PartnerEntity partner = partnerApi.findPartner(request.getPartnerId());
        Assert.notNull(partner, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "商家ID错误");
        Assert.isTrue(request.getChannelId() > 0 && request.getChannelId() <= MTChannelEnum.values().length, ResponseConsts.MEMBER_PARAM_ERROR, "渠道来源错误");
        Assert.isTrue(request.getBody().getUserType() > 0 && request.getBody().getUserType() <= MTUserTypeEnum.values().length, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "⽤户类型错误");
        if (request.getBody().getUserType() == MTUserTypeEnum.PERSONAL.getValue()) {
            Assert.isTrue(ValidUtil.isMobile(request.getBody().getPhone()), ResponseConsts.MEMBER_AUDIT_INFORMATION_ERROR, "手机号输入错误");
        }
        if (ObjectUtils.allNotNull(request.getBody().getOrgCode())) {
            Assert.isTrue(ValidUtil.uniformSocialCreditCodeValidate(request.getBody().getOrgCode()), ResponseConsts.MEMBER_AUDIT_INFORMATION_ERROR, "组织机构代码输入错误");
        }


        String messageCode = "";
        String telephone = "";
        SendsmsCodeResponse response = new SendsmsCodeResponse();

        telephone = request.getBody().getPhone();

        if (request.getBody().getUserType() == MTUserTypeEnum.TOUR_GROUP.getValue() || request.getBody().getUserType() == MTUserTypeEnum.SPECIAL_GROUP.getValue()) {
            OrganizationEntity organization = organizationService.findGroupByOrgCodeAndUserType(request.getBody().getOrgCode(), request.getBody().getUserType());
            if (ObjectUtils.allNotNull(organization)) {
                telephone = organization.getPhone();
            } else {
                Assert.fail(ResponseConsts.MEMBER_USERINFO_NOT_EXIST, "账户⽆效");
            }
        }
        if (ObjectUtils.allNotNull(RedisCache.db().get(request.getBody().getSource() + telephone + flag))) {
            Assert.fail(ResponseConsts.MEMBER_SMSCODE_LIMIT, "发送频次过⾼");
        }

        try {
            messageCode = getRandomCode(4);
            String smsValue = "";

            List<SmsTempletEntity> smsTemplateList = smsTemplateService.findAllSmsTemplate();
//            for (SmsTempletEntity s : smsTemplateList) {
//                if (s.getSmsKey().equals(SmsKeyEnum.SMS_CODE.value())) {
//                    smsValue = s.getSmsValue();
//                }
//            }
//            String context = String.format(smsValue, messageCode);

            for (SmsTempletEntity s : smsTemplateList) {

                //1.团队注册 group_register 2.忘记密码 forget_password 3.散客登录注册 login_register
                //1.团队注册
                if(request.getBody().getSource().trim().toLowerCase().equals("group_register")){
                    //【西安博物院】您本次申请团队账号的验证码为#code#，有效期5分钟。
                    //userType   用户类型： 0 全部  1散客 2 团队
                    //teamType   团队类型： 1旅行社  2学校  3其他
                    if (s.getSmsKey().equals(SmsKeyEnum.SMS_REGIST.value()) && s.getUserType()==2 && s.getTeamType()==1) {
                        smsValue = s.getSmsValue();
                    }
                }

                //2.忘记密码
                if(request.getBody().getSource().trim().toLowerCase().equals("forget_password")){
                    //【西安博物院】您本次申请重置密码的验证码为：%s，有效期5分钟。
                    //userType   用户类型： 0 全部  1散客 2 团队
                    //teamType   团队类型： 1旅行社  2学校  3其他
                    if (s.getSmsKey().equals(SmsKeyEnum.SMS_RESET_PASSWORD.value()) && s.getUserType()==2 && s.getTeamType()==1) {
                        smsValue = s.getSmsValue();
                    }
                }

            }
            String context = String.format(smsValue, messageCode);


            SmsBean bean = new SmsBean(telephone, context);
            //手机短信验证码
            RedisQueue.db().lstPush(RedisKey.SMS_QUEUE, JsonUtil.toJson(bean));

            //添加校验短信验证码
            RedisCache.db().set(request.getBody().getSource() + telephone, messageCode, 300);


            //限制相同手机号相同类型每分钟只能发送一条短信
            RedisCache.db().set(request.getBody().getSource() + telephone + "_flag", "限制相同手机号相同类型每分钟只能发送一条短信", 60);

//            String key="";
//            //.团队注册 group_register 2.忘记密码 forget_password 3.散客登录注册 login_register
//            if(request.getBody().getSource().equals("1")){
//                key= MTSourceEnum.GROUP_REGISTER.getValue();
//            }else  if(request.getBody().getSource().equals("2")){
//                key=MTSourceEnum.RESET_PASSWORD.getValue();
//            }else  if(request.getBody().getSource().equals("3")){
//                key=MTSourceEnum.PERSONAL_REGISTER.getValue();
//            }
//            RedisCache.db().set(key+ telephone, messageCode, 300);
//
//
//            String result = smsSendService.SmsSend(telephone, context);
//            if (result.equals("00")) {
//                response.setCode(ResponseConsts.SUCCESS);
//                response.setDescribe("success");
//            }else{
//                response.setCode(ResponseConsts.SYSTEM_ERROR);
//                response.setDescribe(SMSCodeEnum.getDesc(result));
//            }

            response.setCode(ResponseConsts.SUCCESS);
            response.setDescribe("success");
            response.setPartnerId(request.getPartnerId());
            response.setChannelId(request.getChannelId());
        } catch (AssertionError e) {
            e.printStackTrace();
            Assert.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION);
        }
        return response;
    }*/

    private String getRandomCode(int count) {

        //字符串变量
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int index = (int) Math.round(Math.random() * 9);
            stringBuilder.append(index);
        }
        return stringBuilder.toString();
    }
}

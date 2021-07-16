package cn.com.ktm.mt.controller;

import cn.com.ktm.mt.model.entity.OrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.com.ktm.mt.model.exception.AssertError;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.message.member.groupLogin.request.GroupLoginRequest;
import cn.com.ktm.mt.model.message.member.validcode.request.ValidCodeRequest;
import cn.com.ktm.mt.module.MemberModule;
import cn.com.ktm.mt.module.SmsCodeModule;
import cn.com.ktm.mt.module.ValidCodeModule;
import lombok.extern.log4j.Log4j;

/**
 * @author yuanpeng
 * @date 2020/02/20
 */
@RestController
@Log4j
public class MemberApiImpl {
    @Autowired
    private MemberModule memberModule;

    @Autowired
    private ValidCodeModule validCodeModule;

    @Autowired
    private SmsCodeModule smsCodeModule;

   /* @PostMapping(value = "/personal/login", consumes = "application/json")
    public OtaResponse personalLogin(@RequestBody PersonalLoginRequest request) {
        OtaResponse process;
        try {
            process = memberModule.personalLogin(request);

        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(), request.getPartnerId(), request.getChannelId());
        }
        return process;
    }
*/
   /* @PostMapping(path = "/group/register", consumes = "application/json")
    public OtaResponse groupRegister(@RequestBody GroupRegisterRequest request) {
        OtaResponse process;
        try {
            process = memberModule.groupRegister(request);

        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(), request.getPartnerId(), request.getChannelId());
        }
        return process;

    }*/

   /* @PostMapping(path = "/group/login", consumes = "application/json")
    public OtaResponse groupLogin(@RequestBody GroupLoginRequest request) {
        OtaResponse process;
        try {
            process = memberModule.groupLogin(request);

        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(), request.getPartnerId(), request.getChannelId());
        }
        return process;

    }
*/

    @PostMapping(path = "/validCode", consumes = "application/json")
    public byte[] getValidCode(@RequestBody ValidCodeRequest request) {

        byte[] bytes = null;
        try {
            bytes = validCodeModule.getValidCode(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;

    }

   /* @PostMapping(path = "/user/info", consumes = "application/json")
    public OtaResponse getUserInfo(@RequestBody UserInfoRequest request) {

        OtaResponse process;
        try {
            process = memberModule.getUserInfo(request);

        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(), request.getPartnerId(), request.getChannelId());
        }
        return process;
    }*/


   /* @PostMapping(path = "/reset/password", consumes = "application/json")
    public OtaResponse resetPassword(@RequestBody ResetPasswordRequest request) {

        OtaResponse process;
        try {
            process = memberModule.resetPassword(request);

        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(), request.getPartnerId(), request.getChannelId());
        }
        return process;
    }*/

   /* @PostMapping(path = "/smsCode", consumes = "application/json")
    public OtaResponse SendSmsCode(@RequestBody SendSmsVeriﬁcationCodeRequest request) {

        OtaResponse process;
        try {
            process = smsCodeModule.sendSmsCode(request);

        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(), request.getPartnerId(), request.getChannelId());
        }
        return process;
    }*/



   /* @PostMapping(path = "/group/info", consumes = "application/json")
    public OrganizationEntity findGroupInfo(@RequestParam("userId") String userId) {

        return memberModule.findGroupInfo(userId);

    }*/
}


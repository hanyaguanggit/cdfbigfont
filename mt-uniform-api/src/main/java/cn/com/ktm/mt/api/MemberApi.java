package cn.com.ktm.mt.api;


import cn.com.ktm.mt.model.entity.OrganizationEntity;
import cn.com.ktm.mt.model.message.member.announcement.QueryAnnouncementListRequest;
import cn.com.ktm.mt.model.message.member.announcement.info.QueryAnnoInfoRequest;
import cn.com.ktm.mt.model.message.member.groupLogin.request.GroupLoginRequest;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.message.member.sync.SyncMTGroupAduitBean;
import cn.com.ktm.mt.model.message.member.validcode.request.ValidCodeRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuanpeng
 * @date 2020/02/20
 */
@FeignClient(value = "mt-uniform-member", fallbackFactory = MemberApiFallBackFactory.class)
public interface MemberApi {
    /**
     * 官⽹/官微端散客注册&登录统⼀请求
     *
     * @param request
     * @return OtaResponse
     */
 /*   @ResponseBody
    @PostMapping(path = "/personal/login", consumes = "application/json")
    OtaResponse personalLogin(@RequestBody PersonalLoginRequest request);*/


    /**
     * 旅⾏社/特殊团队登录
     *
     * @param request
     * @return OtaResponse
     */
    @ResponseBody
    @PostMapping(path = "/group/login", consumes = "application/json")
    OtaResponse groupLogin(@RequestBody GroupLoginRequest request);

    /**
     * 获取短信验证码
     *
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping(path = "/validCode", consumes = "application/json")
    byte[] getValidCode(@RequestBody ValidCodeRequest request);

    /**
     * ⽤户信息查询
     *
     * @param request
     * @return OtaResponse
     */
   /* @ResponseBody
    @PostMapping(path = "/user/info", consumes = "application/json")
    OtaResponse queryUserInfo(@RequestBody UserInfoRequest request);*/

    /**
     * 忘记密码
     *
     * @param request
     * @return OtaResponse
     */
/*    @ResponseBody
    @PostMapping(path = "/reset/password", consumes = "application/json")
    OtaResponse resetPassword(@RequestBody ResetPasswordRequest request);*/

    /**
     * 获取短信验证码
     *
     * @param
     * @return OtaResponse
     */
/*    @ResponseBody
    @PostMapping(path = "/smsCode", consumes = "application/json")
    OtaResponse sendSmsCode(@RequestBody SendSmsVeriﬁcationCodeRequest request);*/

    @ResponseBody
    @PostMapping(path = "/group/info", consumes = "application/json")
    OrganizationEntity findGroupInfo(@RequestParam("userId") String userId);

    @ResponseBody
    @RequestMapping(path = "/syncMTGroupAduit", consumes = "application/json")
    OtaResponse syncMTGroupAduit(@RequestBody SyncMTGroupAduitBean srequest);

    @ResponseBody
    @RequestMapping(value = "/queryAnnouncementList", consumes = "application/json")
    OtaResponse queryAnnouncementList(@RequestBody QueryAnnouncementListRequest sb);

    @RequestMapping(value = "/queryAnnouncementInfo", consumes = "application/json")
    public OtaResponse queryAnnouncementInfo(@RequestBody QueryAnnoInfoRequest sb);

}
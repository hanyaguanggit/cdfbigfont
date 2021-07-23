package cn.com.ktm.mt.controller;

import cn.com.ktm.mt.mapper.CsSecurityRoleMapper;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.AssertError;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.message.member.resetpassword.request.ResetPasswordRequest;
import cn.com.ktm.mt.model.security.request.*;
import cn.com.ktm.mt.model.security.request.user.FindSecurityUserListReq;
import cn.com.ktm.mt.module.CsSecurityRoleModule;
import cn.com.ktm.mt.module.CsSecurityUserModule;
import cn.com.ktm.mt.module.CsSecurityUserRoleModule;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class CsSecurityUserController {
    @Autowired
    private CsSecurityUserModule csSecurityUserModule;

    @Autowired
    private CsSecurityRoleModule csSecurityRoleModule;

    @Autowired
    private CsSecurityUserRoleModule csSecurityUserRoleModule;


    /**
     * 后台--系统管理员列表
     * @param request
     * @return
     */
    @PostMapping(value = "admin/findUserList", consumes = "application/json")
    public OtaResponse selectUserList(@RequestBody FindSecurityUserListReq request) {
        OtaResponse process = new OtaResponse<Integer>();
        try {
            process = csSecurityUserModule.selectUserListByCondition(request);
        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(),null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return process;
    }



    /**
     * 后台--登录
     * @param request
     * @return
     */
    @PostMapping(value = "admin/login", consumes = "application/json")
    public OtaResponse findMenuByUserId(@RequestBody LoginRequestVo request) {
        OtaResponse process = new OtaResponse<>();
        try {
            process = csSecurityUserModule.adminLogin(request);
            //测试获取验证码
           /*String validCode = ValidCodeModule.generateRandomCode(4);
            log.info("验证码为：{}",validCode);
           */
        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(),null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return process;
    }

    /**
     * 后台--修改密码
     * @param request
     * @return
     */
    @PostMapping(value = "admin/resetPass", consumes = "application/json")
    public OtaResponse resetPass(@RequestBody ResetPasswordRequest request) {
        OtaResponse process = new OtaResponse<>();
        try {

            process = csSecurityUserModule.resetPassword(request);

        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(),null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return process;
    }

    /**
     * 后台--添加角色
     * @param request
     * @return
     */
    @PostMapping(value = "admin/addRole", consumes = "application/json")
    public OtaResponse addRole(@RequestBody SecurityRoleReqVo request) {
        OtaResponse process = new OtaResponse<Integer>();
        try {
            csSecurityRoleModule.addCsSecurityRole(request);
        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(),null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return process;
    }


    /**
     * 后台--绑定用户角色
     * @param request
     * @return
     */
    @PostMapping(value = "admin/bindUserRole", consumes = "application/json")
    public OtaResponse bindUserRole(@RequestBody BindUserRoleReqVo request) {
        OtaResponse process = new OtaResponse<Integer>();
        try {
           process = csSecurityUserRoleModule.bindUserRole(request);
        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(),null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return process;
    }

    /**
     * 后台--解绑用户角色
     * @param request
     * @return
     */
    @PostMapping(value = "admin/unbindUserRole", consumes = "application/json")
    public OtaResponse unbindUserRole(@RequestBody UnBindUserRoleReqVo request) {
        OtaResponse process = new OtaResponse<Integer>();
        try {
            process = csSecurityUserRoleModule.unbindUserRole(request);
        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(),null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return process;
    }

}

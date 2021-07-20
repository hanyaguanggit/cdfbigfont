package cn.com.ktm.mt.controller;

import cn.com.ktm.mt.mapper.CsSecurityRoleMapper;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.AssertError;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.message.member.resetpassword.request.ResetPasswordRequest;
import cn.com.ktm.mt.model.security.request.LoginRequestVo;
import cn.com.ktm.mt.model.security.request.ResetPassRequestVo;
import cn.com.ktm.mt.model.security.request.SecurityRoleReqVo;
import cn.com.ktm.mt.module.CsSecurityRoleModule;
import cn.com.ktm.mt.module.CsSecurityUserModule;
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
            if(csSecurityRoleModule.addCsSecurityRole(request) > 0){
                process.setDescribe("添加角色成功。");
                process.setCode(ResponseConsts.SUCCESS);
            }else{
                process.setDescribe("添加角色失败。");
                process.setCode(ResponseConsts.ERROR);
            }
        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(),null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return process;
    }
}

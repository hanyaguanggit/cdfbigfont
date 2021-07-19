package cn.com.ktm.mt.controller;

import cn.com.ktm.mt.model.exception.AssertError;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.message.member.resetpassword.request.ResetPasswordRequest;
import cn.com.ktm.mt.model.security.request.LoginRequestVo;
import cn.com.ktm.mt.model.security.request.ResetPassRequestVo;
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


    @PostMapping(value = "admin/resetPass", consumes = "application/json")
    public OtaResponse findMenuByUserId(@RequestBody ResetPasswordRequest request) {
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
}

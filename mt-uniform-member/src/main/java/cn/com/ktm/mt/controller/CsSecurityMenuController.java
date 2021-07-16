package cn.com.ktm.mt.controller;

import cn.com.ktm.mt.model.exception.AssertError;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.security.request.LoginRequestVoBody;
import cn.com.ktm.mt.module.SecurityMenuModule;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class CsSecurityMenuController {
    @Autowired
    private SecurityMenuModule securityMenuModule;


    @PostMapping(value = "member/login", consumes = "application/json")
    public OtaResponse findMenuByUserId(@RequestBody LoginRequestVoBody request) {
        OtaResponse process = new OtaResponse<>();
        try {
            process = securityMenuModule.findMenuByUserId(request);

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
}

package cn.com.ktm.mt.controller;

import cn.com.ktm.mt.model.exception.AssertError;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.security.request.LoginRequestVo;
import cn.com.ktm.mt.model.security.request.LoginRequestVoBody;
import cn.com.ktm.mt.model.security.request.SecurityRoleMenuReqVo;
import cn.com.ktm.mt.model.security.request.SecurityRoleReqVo;
import cn.com.ktm.mt.module.CsSecurityRoleMenuModule;
import cn.com.ktm.mt.module.CsSecurityUserModule;
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
    private CsSecurityRoleMenuModule csSecurityRoleMenuModule;


    /**
     * 后台--角色权限
     * @param request
     * @return
     */
    @PostMapping(value = "admin/keepRoleMenu", consumes = "application/json")
    public OtaResponse addRole(@RequestBody SecurityRoleMenuReqVo request) {
        OtaResponse process = new OtaResponse<Integer>();
        try {
           process = csSecurityRoleMenuModule.keepRoleMenu(request);
        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(),null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return process;
    }

}

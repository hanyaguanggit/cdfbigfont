package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.CsSecurityUser;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.security.request.LoginRequestVoBody;
import cn.com.ktm.mt.model.security.response.LoginResponse;
import cn.com.ktm.mt.model.security.response.LoginResponseBody;
import cn.com.ktm.mt.model.util.utils.security.AesUtils;
import cn.com.ktm.mt.service.CsSecurityUserService;
import cn.com.ktm.mt.service.SecurityMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * hyg
 *权限菜单
 */
@Component
public class SecurityMenuModule {

    Logger logger = LoggerFactory.getLogger(CsSecurityUserModule.class);
    @Autowired
    private SecurityMenuService securityMenuService;

    @Autowired
    private CsSecurityUserService csSecurityUserService;

    public OtaResponse findMenuByUserId(LoginRequestVoBody request)  {
        Assert.notBlank(request.getUserName(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, " 用户名为空");
        Assert.notBlank(request.getPassword(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, "密码为空");
        //先通过用户名和密码查询到用户id，再查询其拥有的菜单权限。
        LoginResponse response = new LoginResponse();
        try {
            String pass = AesUtils.encrypt("",request.getPassword());
            logger.info("解密的字符串:{}",pass);
            CsSecurityUser csSecurityUser = csSecurityUserService.selectByLoginNameAndPassword(request.getUserName(),pass);
            if(csSecurityUser != null){
                List<LoginResponseBody>  menuList = securityMenuService.findMenuByUserId(csSecurityUser.getId());
                if(menuList != null && menuList.size() > 0){
                    logger.info("所拥有的菜单:{}",menuList);
                    response.setMenuList(menuList);
                    response.setCode(ResponseConsts.SUCCESS);
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


}

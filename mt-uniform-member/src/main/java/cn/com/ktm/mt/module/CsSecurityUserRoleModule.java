package cn.com.ktm.mt.module;
import cn.com.ktm.mt.model.bean.CsSecurityUserRole;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.security.request.BindUserRoleReqVo;
import cn.com.ktm.mt.model.security.request.UnBindUserRoleReqVo;
import cn.com.ktm.mt.service.CsSecurityUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CsSecurityUserRoleModule {

    Logger logger = LoggerFactory.getLogger(CsSecurityUserRoleModule.class);
    @Autowired
    private CsSecurityUserRoleService csSecurityUserRoleService;

    /**
     * 绑定用户角色
     * @param request
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public OtaResponse bindUserRole(BindUserRoleReqVo request){
        request.getBody().valid();
        OtaResponse response = new OtaResponse();
        try {
            request.getBody().getUserIds().forEach(ur ->{
                CsSecurityUserRole userRole = new CsSecurityUserRole();
                userRole.setRoleid(request.getBody().getRoleId());
                userRole.setUserid(ur.intValue());
                int var1 = csSecurityUserRoleService.bindSecurityUserRole(userRole);
                Assert.isTrue(var1 > 0, ResponseConsts.CREATE_USER_ROLE_ERROR,"绑定用户角色失败。");
            });
            response.setCode(ResponseConsts.SUCCESS);
            response.setDescribe("绑定用户角色成功。");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 解绑用户角色
     * @param request
     * @return
     */
    public OtaResponse unbindUserRole(UnBindUserRoleReqVo request){
        int unbind = 0;
        request.getBody().valid();
        OtaResponse response = new OtaResponse();
        CsSecurityUserRole info = null;
        try{
             info = csSecurityUserRoleService.selectByRoleIdAndUserId(request.getBody().getRoleId(),request.getBody().getUserId());
            if(info != null){
                unbind = csSecurityUserRoleService.unbindUserRole(info.getId());
                Assert.isTrue(unbind > 0,ResponseConsts.DELETE_USER_ROLR_ERROR,"删除用户角色失败。");
                response.setCode(ResponseConsts.SUCCESS);
                response.setDescribe("解绑成功。");
            }else{
             response.setCode(ResponseConsts.ERROR);
             response.setDescribe("无此关系,解绑失败。");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("解绑用户角色发生错误，用户角色实体：{}",info);
        }
        return response ;
    }
}

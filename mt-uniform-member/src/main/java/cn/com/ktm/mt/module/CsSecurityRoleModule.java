package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.CsSurfacePictureLink;
import cn.com.ktm.mt.model.bean.CsSecurityRole;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.section.request.CsSurfaceSectionRequest;
import cn.com.ktm.mt.model.security.request.SecurityRoleReqVo;
import cn.com.ktm.mt.service.CsSecurityRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CsSecurityRoleModule {
    Logger logger = LoggerFactory.getLogger(CsSecurityRoleModule.class);

    @Autowired
    private CsSecurityRoleService csSecurityRoleService;

    /**
     * hyg
     * 添加角色
     * @param request
     * @return
     */
    public int addCsSecurityRole(SecurityRoleReqVo request){
        int ar =0;
        request.getBody().valid();
        CsSecurityRole csSecurityRole = new CsSecurityRole();
        csSecurityRole.setCode("");
        csSecurityRole.setName(request.getBody().getRoleName());
        csSecurityRole.setCreator(request.getBody().getCreator());
        csSecurityRole.setCreateTime(new Date());
        csSecurityRole.setLastModifiedUser(0);
        csSecurityRole.setLastModifiedTime(new Date());
        try{
           ar= csSecurityRoleService.addCsSecurityRole(csSecurityRole);
        }catch (Exception e){
            logger.error("创建角色失败，csSecurityRole={}",csSecurityRole);
            e.printStackTrace();
        }
        if(csSecurityRole.getId() == null){
            Assert.fail(ResponseConsts.CREATE_ROLE_ERROR,"创建角色失败，id为空。");
        }
        return ar;
    }
}

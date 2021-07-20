package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.CsSurfaceTextLink;
import cn.com.ktm.mt.model.bean.CsSecurityRoleMenu;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.section.request.CsSurfaceSectionRequest;
import cn.com.ktm.mt.model.security.request.SecurityRoleMenuReqVo;
import cn.com.ktm.mt.service.CsSecurityRoleMenuService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j2
public class CsSecurityRoleMenuModule {
    Logger logger = LoggerFactory.getLogger(CsSecurityRoleMenuModule.class);

    @Autowired
    private CsSecurityRoleMenuService csSecurityRoleMenuService;


    /**
     * hyg
     *  保存角色权限
     * @param request
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int keepRoleMenu(SecurityRoleMenuReqVo request){
        request.getBody().valid();

        //1、查询此角色下所拥有的权限，有权限先删除记录再添加。没有权限直接添加。
        List<CsSecurityRoleMenu>  rm = csSecurityRoleMenuService.findRoleMenuByRoleId(request.getBody().getRoleId());
        if(!rm.isEmpty()){
            if(request.getBody().getMenuIdList().isEmpty()){
                //TODO 删除当前角色所有权限
            }else{
                //需要更新权限
            }

        }else{

        }



        CsSecurityRoleMenu csSecurityRoleMenu = new CsSecurityRoleMenu();
        csSecurityRoleMenu.setRoleid(request.getBody().getRoleId());
       // csSecurityRoleMenu.setMenuid(request.getBody().getMenuIdList());


       /* try{
            csSurfaceTextLinkService.addTextLinkModel(csSurfaceTextLink);
        }catch (Exception e){
            logger.error("创建文字链接失败，pictureLinkId={}",csSurfaceTextLink.getId());
            e.printStackTrace();
        }
        if(csSurfaceTextLink.getId() == null){
            Assert.fail(ResponseConsts.CREATE_TEXT_LINK_ERROR,"创建文字链接失败。");
        }*/
        return 1;
    }

}

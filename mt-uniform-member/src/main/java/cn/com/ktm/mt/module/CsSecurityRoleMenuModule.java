package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.CsSurfaceTextLink;
import cn.com.ktm.mt.model.bean.CsSecurityRoleMenu;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.OtaResponse;
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
import java.util.ArrayList;
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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public OtaResponse keepRoleMenu(SecurityRoleMenuReqVo request) {
        request.getBody().valid();
        List<Integer> old = new ArrayList<>();//旧权限id列表
        OtaResponse response = new OtaResponse();
        int a = 0;
        //查询此角色下所拥有的权限，1、比较,新旧权限一样,无需操作。2、有权限先删除记录再添加。没有权限直接添加。
        List<CsSecurityRoleMenu> rmlist = csSecurityRoleMenuService.findRoleMenuByRoleId(request.getBody().getRoleId());
        old.addAll(rmlist.stream().map(r -> r.getMenuid()).collect(Collectors.toList()));
        logger.info("旧权限菜单id集合：{}", old);

        //不允许添加重复的权限菜单，去重。
        List<Integer> newMenuList = request.getBody().getMenuIdList().stream().distinct().collect(Collectors.toList());
        logger.info("去重后的菜单id列表：{}",newMenuList);
        //判断两个集合是否相同
        boolean issame = old.stream().sorted().collect(Collectors.toList())
                .equals(newMenuList.stream().sorted().collect(Collectors.toList()));
        if (issame) {
            response.setCode(ResponseConsts.ERROR);
            response.setDescribe("该角色权限无变更,无需保存");
            return response;
        }
        if (!rmlist.isEmpty()) {//不为空
            for (CsSecurityRoleMenu rm : rmlist) {
                int d = csSecurityRoleMenuService.deleteRoleMenu(rm.getId());
                Assert.isTrue(d > 0, ResponseConsts.DELETE_ROLR_MENU_ERROR, "更新角色权限异常。");
            }
            if (request.getBody().getMenuIdList().size() > 0) {
                // 添加当前角色权限
                a = addroleMenu(newMenuList, request.getBody().getRoleId());
            } else {
                a = 1;//删除了权限
            }
        } else {//为空
            if (request.getBody().getMenuIdList().size() > 0) {
                a = addroleMenu(newMenuList, request.getBody().getRoleId());
            }
        }
        if (a > 0) {
            response.setCode(ResponseConsts.SUCCESS);
            response.setDescribe("保存角色权限成功。");
        } else {
            response.setCode(ResponseConsts.ERROR);
            response.setDescribe("保存角色权限失败。");
        }
        return response;
    }

    /**
     * 添加角色权限
     * @param ids
     * @param roleid
     * @return int
     */
    public int addroleMenu(List<Integer> ids, Integer roleid) {
        if (ids.size() > 0) {
            ids.forEach(add -> {
                CsSecurityRoleMenu rm = new CsSecurityRoleMenu();
                rm.setRoleid(roleid);
                rm.setMenuid(add.intValue());
                int a = csSecurityRoleMenuService.addRoleMenu(rm);
                Assert.isTrue(a > 0, ResponseConsts.CREATE_ROLE_MENU_ERROR, "更新角色权限信息异常。");
            });
            return 1;
        }
        return 0;
    }


}

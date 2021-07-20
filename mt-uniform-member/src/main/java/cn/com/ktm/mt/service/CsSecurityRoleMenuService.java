package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSecurityRoleMenuMapper;
import cn.com.ktm.mt.model.bean.CsSecurityRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsSecurityRoleMenuService {
    @Autowired
    private CsSecurityRoleMenuMapper csSecurityRoleMenuMapper;

    public int addRoleMenu(CsSecurityRoleMenu csSecurityRoleMenu){
        int a = csSecurityRoleMenuMapper.insert(csSecurityRoleMenu);
        return a > 0 ? a : 0;
    }

    public int deleteRoleMenu(int roleId){
        int d = csSecurityRoleMenuMapper.deleteByPrimaryKey(roleId);
        return d > 0 ? d : 0;
    }

    public List<CsSecurityRoleMenu> findRoleMenuByRoleId(Integer roleId){
        List<CsSecurityRoleMenu> csSecurityRoleMenuList = csSecurityRoleMenuMapper.selectByRoleId(roleId);
        return csSecurityRoleMenuList;
    }
}

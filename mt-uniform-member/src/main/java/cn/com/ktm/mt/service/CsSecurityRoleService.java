package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSecurityRoleMapper;
import cn.com.ktm.mt.model.bean.CsSecurityRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsSecurityRoleService {
    @Autowired
    private CsSecurityRoleMapper csSecurityRoleMapper;

    public int addCsSecurityRole(CsSecurityRole csSecurityRole){
        int r = csSecurityRoleMapper.insertSelective(csSecurityRole);
        return r > 0 ? r : 0;
    }
}

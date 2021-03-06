package cn.com.ktm.mt.service;
import cn.com.ktm.mt.mapper.CsSecurityUserRoleMapper;
import cn.com.ktm.mt.model.bean.CsSecurityUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsSecurityUserRoleService {
    @Autowired
    private CsSecurityUserRoleMapper csSecurityUserRoleMapper;

    public int bindSecurityUserRole(CsSecurityUserRole csSecurityUserRole){
      int add = csSecurityUserRoleMapper.insert(csSecurityUserRole);
      return add > 0?add:0;
    }
    public CsSecurityUserRole selectByRoleIdAndUserId(Integer roleid, Integer userid){
        CsSecurityUserRole ur = csSecurityUserRoleMapper.selectByRoleIdAndUserId(roleid,userid);
        return ur;
    }

    public int unbindUserRole(Integer id ){
        int del = csSecurityUserRoleMapper.deleteByPrimaryKey(id);
        return del > 0?del:0;
    }

    public List<CsSecurityUserRole> selectUseRoleByUserId(Integer userid){
       List<CsSecurityUserRole> urlist = csSecurityUserRoleMapper.selectByUserId(userid);
       return urlist;
    }
}

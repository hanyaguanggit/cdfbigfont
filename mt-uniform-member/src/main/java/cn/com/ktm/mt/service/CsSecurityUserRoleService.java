package cn.com.ktm.mt.service;
import cn.com.ktm.mt.mapper.CsSecurityUserRoleMapper;
import cn.com.ktm.mt.model.bean.CsSecurityUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsSecurityUserRoleService {
    @Autowired
    private CsSecurityUserRoleMapper csSecurityUserRoleMapper;

    public int addSecurityUserRole(CsSecurityUserRole csSecurityUserRole){
      int add = csSecurityUserRoleMapper.insertSelective(csSecurityUserRole);
      return add > 0?add:0;
    }
}

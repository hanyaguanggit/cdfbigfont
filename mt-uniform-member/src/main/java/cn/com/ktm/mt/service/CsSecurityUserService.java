package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSecurityUserMapper;
import cn.com.ktm.mt.model.CsSecurityUser;
import cn.com.ktm.mt.model.security.request.LoginRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsSecurityUserService {
    @Autowired
    private CsSecurityUserMapper csSecurityUserMapper;

    public CsSecurityUser selectByLoginNameAndPassword(String loginName,String password){
        return csSecurityUserMapper.selectByLoginNameAndPassword(loginName,password);
    };
}

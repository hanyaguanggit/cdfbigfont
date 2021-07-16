package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSecurityMenuMapper;
import cn.com.ktm.mt.model.security.response.LoginResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityMenuService {
    @Autowired
    private CsSecurityMenuMapper csSecurityMenuMapper;

    public List<LoginResponseBody> findMenuByUserId(Integer userId){
        return csSecurityMenuMapper.findMenuByUserId(userId);
    };
}

package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSecurityUserMapper;
import cn.com.ktm.mt.model.bean.CsSecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsSecurityUserService {
    @Autowired
    private CsSecurityUserMapper csSecurityUserMapper;

    public CsSecurityUser selectByLoginNameAndPassword(String loginName,String password){
        return csSecurityUserMapper.selectByLoginNameAndPassword(loginName,password);
    };

   public CsSecurityUser selectByUserIdAndPassword(int userId,String password){
       return csSecurityUserMapper.selectByUserIdAndPassword(userId,password);
   }

   public int updateUserById(int userId){
       return csSecurityUserMapper.updateUserById(userId);
   };

   public int updateByPrimaryKeySelective(CsSecurityUser csSecurityUser){
       return csSecurityUserMapper.updateByPrimaryKeySelective(csSecurityUser);
   }
}

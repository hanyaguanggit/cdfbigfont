package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSecurityUserMapper;
import cn.com.ktm.mt.model.bean.CsSecurityUser;
import cn.com.ktm.mt.model.security.response.FindSecurityUserListResModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
   public List<FindSecurityUserListResModel> selectUserListByCondition(String searchKey, String searchContent){
       return csSecurityUserMapper.selectUserListByCondition(searchKey,searchContent);
   };
   public int insertUser(CsSecurityUser csSecurityUser){
       return csSecurityUserMapper.insert(csSecurityUser);
   }
   public CsSecurityUser selectUserById(int userId){
       return csSecurityUserMapper.selectByPrimaryKey(userId);
   }
   public int updateUserLockById(Integer locked,int userId){
       return csSecurityUserMapper.updateUserLockById(locked,userId);
   }
   public int updateUserEnabledById(Integer enabled,int userId){
       return csSecurityUserMapper.updateUserEnabledById(enabled,userId);
   }

}

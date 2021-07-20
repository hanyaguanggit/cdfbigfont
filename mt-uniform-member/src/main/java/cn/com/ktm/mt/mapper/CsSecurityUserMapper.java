package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.bean.CsSecurityUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CsSecurityUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSecurityUser record);

    int insertSelective(CsSecurityUser record);

    CsSecurityUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSecurityUser record);

    int updateByPrimaryKey(CsSecurityUser record);

    //后台登录，通过用户名和密码查询信息
    CsSecurityUser selectByLoginNameAndPassword(@Param("loginName") String loginName,@Param("password") String password);

    CsSecurityUser selectByUserIdAndPassword(@Param("id")int userId,@Param("password") String password);

    int updateUserById(@Param("id")int userId);
}
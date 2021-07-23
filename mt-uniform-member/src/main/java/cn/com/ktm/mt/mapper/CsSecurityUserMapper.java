package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.bean.CsSecurityUser;
import cn.com.ktm.mt.model.security.response.FindSecurityUserListResModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    //查询列表
    List<FindSecurityUserListResModel> selectUserListByCondition(@Param("searchKey") String searchKey, @Param("searchContent") String searchContent);
}
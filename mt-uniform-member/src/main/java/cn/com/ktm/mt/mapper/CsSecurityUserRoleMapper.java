package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.bean.CsSecurityUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CsSecurityUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSecurityUserRole record);

    int insertSelective(CsSecurityUserRole record);

    CsSecurityUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSecurityUserRole record);

    int updateByPrimaryKey(CsSecurityUserRole record);

    CsSecurityUserRole selectByRoleIdAndUserId(@Param("roleid") Integer roleid,@Param("userid") Integer userid);

    List<CsSecurityUserRole> selectByUserId(Integer userId);
}
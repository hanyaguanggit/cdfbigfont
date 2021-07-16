package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsSecurityUserRole;

public interface CsSecurityUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSecurityUserRole record);

    int insertSelective(CsSecurityUserRole record);

    CsSecurityUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSecurityUserRole record);

    int updateByPrimaryKey(CsSecurityUserRole record);
}
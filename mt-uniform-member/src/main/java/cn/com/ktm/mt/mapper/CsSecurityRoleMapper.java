package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsSecurityRole;

public interface CsSecurityRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSecurityRole record);

    int insertSelective(CsSecurityRole record);

    CsSecurityRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSecurityRole record);

    int updateByPrimaryKey(CsSecurityRole record);
}
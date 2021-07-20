package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.bean.CsSecurityRoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CsSecurityRoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSecurityRoleMenu record);

    int insertSelective(CsSecurityRoleMenu record);

    CsSecurityRoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSecurityRoleMenu record);

    int updateByPrimaryKey(CsSecurityRoleMenu record);

    List<CsSecurityRoleMenu> selectByRoleId(Integer roleid);
}
package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.bean.CsSecurityRole;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public interface CsSecurityRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSecurityRole record);

    int insertSelective(CsSecurityRole record);

    CsSecurityRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSecurityRole record);

    int updateByPrimaryKey(CsSecurityRole record);
}
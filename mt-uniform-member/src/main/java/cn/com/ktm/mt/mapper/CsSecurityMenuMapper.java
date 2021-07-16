package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsSecurityMenu;
import cn.com.ktm.mt.model.security.response.LoginResponseBody;

import java.util.List;

public interface CsSecurityMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSecurityMenu record);

    int insertSelective(CsSecurityMenu record);

    CsSecurityMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSecurityMenu record);

    int updateByPrimaryKey(CsSecurityMenu record);
    //根据用户id查询权限菜单
    List<LoginResponseBody> findMenuByUserId(Integer userId);
}
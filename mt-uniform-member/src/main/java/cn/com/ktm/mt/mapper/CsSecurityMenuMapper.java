package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.bean.CsSecurityMenu;
import cn.com.ktm.mt.model.security.response.LoginResponseBody;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CsSecurityMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSecurityMenu record);

    int insertSelective(CsSecurityMenu record);

    CsSecurityMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSecurityMenu record);

    int updateByPrimaryKey(CsSecurityMenu record);

    List<LoginResponseBody> findMenuByUserId(Integer userId);
}
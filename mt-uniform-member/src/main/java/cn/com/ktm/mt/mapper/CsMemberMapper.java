package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.bean.CsMember;

public interface CsMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsMember record);

    int insertSelective(CsMember record);

    CsMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsMember record);

    int updateByPrimaryKey(CsMember record);
}
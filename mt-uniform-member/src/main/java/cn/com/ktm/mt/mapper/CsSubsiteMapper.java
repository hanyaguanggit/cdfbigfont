package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsSubsite;

public interface CsSubsiteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSubsite record);

    int insertSelective(CsSubsite record);

    CsSubsite selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSubsite record);

    int updateByPrimaryKey(CsSubsite record);
}
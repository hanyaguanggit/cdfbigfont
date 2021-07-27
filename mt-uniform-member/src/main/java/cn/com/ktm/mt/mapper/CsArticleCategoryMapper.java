package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.bean.CsArticleCategory;

public interface CsArticleCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsArticleCategory record);

    int insertSelective(CsArticleCategory record);

    CsArticleCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsArticleCategory record);

    int updateByPrimaryKey(CsArticleCategory record);
}
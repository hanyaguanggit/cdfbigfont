package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.bean.CsArticleCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CsArticleCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsArticleCategory record);

    int insertSelective(CsArticleCategory record);

    CsArticleCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsArticleCategory record);

    int updateByPrimaryKey(CsArticleCategory record);

    List<CsArticleCategory> selectByShopId(Integer shopId);
}
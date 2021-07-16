package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsSurfaceRecommendArticle;
import org.springframework.stereotype.Repository;

@Repository
public interface CsSurfaceRecommendArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSurfaceRecommendArticle record);

    int insertSelective(CsSurfaceRecommendArticle record);

    CsSurfaceRecommendArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSurfaceRecommendArticle record);

    int updateByPrimaryKey(CsSurfaceRecommendArticle record);
}
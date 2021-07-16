package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsSurfaceRecommendCategory;

public interface CsSurfaceRecommendCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSurfaceRecommendCategory record);

    int insertSelective(CsSurfaceRecommendCategory record);

    CsSurfaceRecommendCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSurfaceRecommendCategory record);

    int updateByPrimaryKey(CsSurfaceRecommendCategory record);
}
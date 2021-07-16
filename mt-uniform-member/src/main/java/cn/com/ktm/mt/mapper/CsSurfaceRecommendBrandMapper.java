package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsSurfaceRecommendBrand;
import org.springframework.stereotype.Repository;

@Repository
public interface CsSurfaceRecommendBrandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSurfaceRecommendBrand record);

    int insertSelective(CsSurfaceRecommendBrand record);

    CsSurfaceRecommendBrand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSurfaceRecommendBrand record);

    int updateByPrimaryKey(CsSurfaceRecommendBrand record);
}
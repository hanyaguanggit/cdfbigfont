package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsSurfaceRecommendProduct;
import org.springframework.stereotype.Repository;

@Repository
public interface CsSurfaceRecommendProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSurfaceRecommendProduct record);

    int insertSelective(CsSurfaceRecommendProduct record);

    CsSurfaceRecommendProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSurfaceRecommendProduct record);

    int updateByPrimaryKey(CsSurfaceRecommendProduct record);
}
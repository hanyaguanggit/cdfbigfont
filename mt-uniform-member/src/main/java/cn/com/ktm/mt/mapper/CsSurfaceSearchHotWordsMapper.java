package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsSurfaceSearchHotWords;
import org.springframework.stereotype.Repository;

@Repository
public interface CsSurfaceSearchHotWordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSurfaceSearchHotWords record);

    int insertSelective(CsSurfaceSearchHotWords record);

    CsSurfaceSearchHotWords selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSurfaceSearchHotWords record);

    int updateByPrimaryKey(CsSurfaceSearchHotWords record);
}
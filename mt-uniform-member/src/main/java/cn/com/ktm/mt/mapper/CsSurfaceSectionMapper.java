package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsSurfaceSection;
import org.springframework.stereotype.Repository;

@Repository
public interface CsSurfaceSectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSurfaceSection record);

    int insertSelective(CsSurfaceSection record);

    CsSurfaceSection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSurfaceSection record);

    int updateByPrimaryKeyWithBLOBs(CsSurfaceSection record);

    int updateByPrimaryKey(CsSurfaceSection record);
}
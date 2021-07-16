package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsSurfaceSectionTemplateConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface CsSurfaceSectionTemplateConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSurfaceSectionTemplateConfig record);

    int insertSelective(CsSurfaceSectionTemplateConfig record);

    CsSurfaceSectionTemplateConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSurfaceSectionTemplateConfig record);

    int updateByPrimaryKey(CsSurfaceSectionTemplateConfig record);
}
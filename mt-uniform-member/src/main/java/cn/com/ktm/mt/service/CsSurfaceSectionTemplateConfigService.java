package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSurfaceSectionTemplateConfigMapper;
import cn.com.ktm.mt.model.CsSurfaceSectionTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsSurfaceSectionTemplateConfigService {
    @Autowired
    private CsSurfaceSectionTemplateConfigMapper csSurfaceSectionTemplateConfigMapper;

    public int addCsSurfaceSectionTemplateConfig(CsSurfaceSectionTemplateConfig csSurfaceSectionTemplateConfig){
      int add = csSurfaceSectionTemplateConfigMapper.insertSelective(csSurfaceSectionTemplateConfig)  ;
      return add > 0 ? add:0;
    };
}

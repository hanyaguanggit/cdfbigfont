package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSurfaceSectionMapper;
import cn.com.ktm.mt.model.CsSurfaceSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsSurfaceSectionService {
    @Autowired
    private CsSurfaceSectionMapper csSurfaceSectionMapper;

    public int addCsSurfaceSection(CsSurfaceSection csSurfaceSection){
       int add = csSurfaceSectionMapper.insertSelective(csSurfaceSection);
       return add > 0 ? add : 0;
    }
}

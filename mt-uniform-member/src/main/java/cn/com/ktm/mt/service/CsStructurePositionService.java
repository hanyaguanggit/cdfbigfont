package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSiteStructureMapper;
import cn.com.ktm.mt.mapper.CsStructurePositionMapper;
import cn.com.ktm.mt.model.CsSiteStructure;
import cn.com.ktm.mt.model.CsStructurePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsStructurePositionService {
    @Autowired
    private CsStructurePositionMapper csStructurePositionMapper;

    public int addCsStructurePosition(CsStructurePosition csStructurePosition){

       int add = csStructurePositionMapper.insertSelective(csStructurePosition);
       return add > 0 ? add:0;
    }
}

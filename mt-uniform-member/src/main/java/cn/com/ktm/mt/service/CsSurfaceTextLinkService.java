package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSurfaceTextLinkMapper;
import cn.com.ktm.mt.model.CsSurfaceTextLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsSurfaceTextLinkService {
    @Autowired
    private CsSurfaceTextLinkMapper csSurfaceTextLinkMapper;

    public int addTextLinkModel(CsSurfaceTextLink csSurfaceTextLink){
       int add = csSurfaceTextLinkMapper.insertSelective(csSurfaceTextLink);
       return add > 0 ? add : 0;
    }
}

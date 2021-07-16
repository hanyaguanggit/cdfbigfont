package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSurfaceRichTextMapper;
import cn.com.ktm.mt.model.CsSurfaceRichText;
import cn.com.ktm.mt.model.CsSurfaceRichTextWithBLOBs;
import cn.com.ktm.mt.model.section.request.CsSurfaceSectionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsSurfaceRichTextService {
    @Autowired
    private CsSurfaceRichTextMapper csSurfaceRichTextMapper;

    public int addCsSurfaceRichText(CsSurfaceRichTextWithBLOBs csSurfaceRichText){
        int add = csSurfaceRichTextMapper.insertSelective(csSurfaceRichText);
        return add > 0 ? add : 0;
    }
}

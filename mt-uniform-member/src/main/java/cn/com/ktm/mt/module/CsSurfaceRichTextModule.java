package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.CsSurfaceRichText;
import cn.com.ktm.mt.model.CsSurfaceRichTextWithBLOBs;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.section.request.CsSurfaceSectionRequest;
import cn.com.ktm.mt.service.CsSurfaceRichTextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsSurfaceRichTextModule {
    Logger logger = LoggerFactory.getLogger(CsSurfaceRichTextModule.class);
    @Autowired
    private CsSurfaceRichTextService csSurfaceRichTextService;

    public int addCsSurfaceRichTextModel(CsSurfaceSectionRequest request){
        int add = 0;
        CsSurfaceRichTextWithBLOBs  csSurfaceRichTextWithBLOBs = new CsSurfaceRichTextWithBLOBs();
        csSurfaceRichTextWithBLOBs.setSectionId(request.getSectionId());
        csSurfaceRichTextWithBLOBs.setContent(request.getRichContent());
        csSurfaceRichTextWithBLOBs.setContentEN(null);
        try{
          add =   csSurfaceRichTextService.addCsSurfaceRichText(csSurfaceRichTextWithBLOBs);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("添加富文本错误，富文本实体内容：{}",csSurfaceRichTextWithBLOBs);
        }
        if(csSurfaceRichTextWithBLOBs.getId() == null){
            Assert.fail(ResponseConsts.CREATE_RICH_TEXT_ERROR,"创建富文本信息失败。");
        }
        return add > 0 ? csSurfaceRichTextWithBLOBs.getId():0;
    }
}

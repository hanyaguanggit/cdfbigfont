package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.CsSurfaceRichTextWithBLOBs;
import cn.com.ktm.mt.model.CsSurfaceSearchHotWords;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.section.request.CsSurfaceSectionRequest;
import cn.com.ktm.mt.service.CsSurfaceSearchHotWordsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsSurfaceSearchHotWordsModule {
    Logger logger = LoggerFactory.getLogger(CsSurfaceSearchHotWordsModule.class);

    @Autowired
    private CsSurfaceSearchHotWordsService csSurfaceSearchHotWordsService;

    public int addCsSurfaceSearchHotWordsModel(CsSurfaceSectionRequest request){
        int add = 0;
        CsSurfaceSearchHotWords csSurfaceSearchHotWords = new CsSurfaceSearchHotWords();
        csSurfaceSearchHotWords.setSectionId(request.getSectionId());
        csSurfaceSearchHotWords.setRecommend(request.getRecommend());
        csSurfaceSearchHotWords.setTitle(request.getSearchText());
        csSurfaceSearchHotWords.setLinkType(request.getLinkType());
        csSurfaceSearchHotWords.setTargetUrl(request.getLinkUrl());
        try{
            add =   csSurfaceSearchHotWordsService.addSearchHotWordsModel(csSurfaceSearchHotWords);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("添加搜索热词错误，搜素热词实体内容：{}",csSurfaceSearchHotWords);
        }
        if(csSurfaceSearchHotWords.getId() == null){
            Assert.fail(ResponseConsts.CREATE_SEARCH_HOT_WORDS_ERROR,"创建搜索热词错误，id为空。");
        }
        return add > 0 ? csSurfaceSearchHotWords.getId():0;
    }
}

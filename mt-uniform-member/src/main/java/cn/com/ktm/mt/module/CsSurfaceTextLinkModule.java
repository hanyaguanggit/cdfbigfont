package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.CsSurfacePictureLink;
import cn.com.ktm.mt.model.CsSurfaceTextLink;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.section.request.CsSurfaceSectionRequest;
import cn.com.ktm.mt.service.CsSurfaceTextLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsSurfaceTextLinkModule {

    Logger logger = LoggerFactory.getLogger(CsSurfaceTextLinkModule.class);
    @Autowired
     private CsSurfaceTextLinkService csSurfaceTextLinkService;

    /**
     * hyg
     *  文字链接
     * @param request
     * @return
     */
    public CsSurfaceTextLink addTextLinkeModel(CsSurfaceSectionRequest request){
        CsSurfaceTextLink csSurfaceTextLink = new CsSurfaceTextLink();
        csSurfaceTextLink.setSectionId(request.getSectionId());
        csSurfaceTextLink.setName(request.getTextName());
        csSurfaceTextLink.setNameEN(null);
        csSurfaceTextLink.setUrl(request.getTextUrl());
        csSurfaceTextLink.setUrlEN(null);

        try{
            csSurfaceTextLinkService.addTextLinkModel(csSurfaceTextLink);
        }catch (Exception e){
            logger.error("创建文字链接失败，pictureLinkId={}",csSurfaceTextLink.getId());
            e.printStackTrace();
        }
        if(csSurfaceTextLink.getId() == null){
            Assert.fail(ResponseConsts.CREATE_TEXT_LINK_ERROR,"创建文字链接失败。");
        }
        return csSurfaceTextLink;
    }
}

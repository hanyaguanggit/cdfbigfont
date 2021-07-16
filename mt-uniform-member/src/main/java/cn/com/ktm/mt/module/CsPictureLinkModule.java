package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.CsSurfacePictureLink;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.section.request.CsSurfaceSectionRequest;
import cn.com.ktm.mt.service.CsSurfacePictureLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsPictureLinkModule {
    Logger logger = LoggerFactory.getLogger(CsPictureLinkModule.class);
    @Autowired
    private CsSurfacePictureLinkService csSurfacePictureLinkService;

    /**
     * hyg
     * 创建具体表 图片链接
     * @param request
     * @return
     */
    public CsSurfacePictureLink addPictureLinkeModel(CsSurfaceSectionRequest request){
        CsSurfacePictureLink csSurfacePictureLink = new CsSurfacePictureLink();
        csSurfacePictureLink.setSectionId(request.getSectionId());
        csSurfacePictureLink.setPicture(request.getPicture());
        csSurfacePictureLink.setPictureEN(request.getPicture());
        csSurfacePictureLink.setUrl(request.getUrl());
        csSurfacePictureLink.setUrlEN(null);
        try{
            csSurfacePictureLinkService.addCsSurfacePictureLink(csSurfacePictureLink);
        }catch (Exception e){
            logger.error("创建图片链接失败，pictureLinkId={}",csSurfacePictureLink.getId());
            e.printStackTrace();
        }
        if(csSurfacePictureLink.getId() == null){
            Assert.fail(ResponseConsts.CREATE_PICTURE_LINK_ERROR,"创建图片链接失败。");
        }
        return csSurfacePictureLink;
    }
}

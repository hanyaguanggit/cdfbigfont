package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.CsSurfaceBestSellerList;
import cn.com.ktm.mt.model.CsSurfacePictureLink;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.section.request.CsSurfaceSectionRequest;
import cn.com.ktm.mt.service.CsSurfaceBestSellerListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsSurfaceBestSellerListModule {

    Logger logger = LoggerFactory.getLogger(CsSurfaceBestSellerListModule.class);
    @Autowired
    private CsSurfaceBestSellerListService csSurfaceBestSellerListService;



    /**
     * hyg
     * 创建具体表 畅销榜单
     * @param request
     * @return
     */
    public CsSurfaceBestSellerList addBestSellerListModel(CsSurfaceSectionRequest request){
        CsSurfaceBestSellerList csSurfaceBestSellerList = new CsSurfaceBestSellerList();
        csSurfaceBestSellerList.setSectionId(request.getSectionId());
        csSurfaceBestSellerList.setPicId(request.getPictureId());
        csSurfaceBestSellerList.setDisplayType(request.getDisplayType());
        csSurfaceBestSellerList.setGoodsIds(request.getGoodsIds());
        try{
            csSurfaceBestSellerListService.addBestSellerListModel(csSurfaceBestSellerList);
        }catch (Exception e){
            logger.error("创建畅销榜单失败,创建实体信息：{}",csSurfaceBestSellerList);
            e.printStackTrace();
        }
        if(csSurfaceBestSellerList.getId() == null){
            Assert.fail(ResponseConsts.CREATE_PICTURE_LINK_ERROR,"创建畅销榜单失败。");
        }
        return csSurfaceBestSellerList;
    }
}

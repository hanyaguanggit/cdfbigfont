package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.CsSurfaceRecommendProduct;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.section.request.CsSurfaceSectionRequest;
import cn.com.ktm.mt.service.CsSurfaceRecommendProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsSurfaceRecommendProductModule {

    Logger logger = LoggerFactory.getLogger(CsSurfaceRecommendProductModule.class);
    @Autowired
    private CsSurfaceRecommendProductService csSurfaceRecommendProductService;

    public int addRecommendProduct(CsSurfaceSectionRequest csSurfaceSectionRequest){
        CsSurfaceRecommendProduct csSurfaceRecommendProduct = new CsSurfaceRecommendProduct();
        int add = 0;
        csSurfaceRecommendProduct.setSectionId(csSurfaceSectionRequest.getSectionId());
        csSurfaceRecommendProduct.setPicId(0);
        csSurfaceRecommendProduct.setProductId(0);
        csSurfaceRecommendProduct.setTitle(csSurfaceSectionRequest.getProductTitle());
        csSurfaceRecommendProduct.setDescription(csSurfaceSectionRequest.getProductDescription());
        csSurfaceRecommendProduct.setProductLink(csSurfaceSectionRequest.getProductLink());
        try{
           add = csSurfaceRecommendProductService.addRecommendProductModel(csSurfaceRecommendProduct);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("添加推荐商品失败，推荐商品实体内容：{}",csSurfaceRecommendProduct);
        }
        if(csSurfaceRecommendProduct.getId() == null){
            Assert.fail(ResponseConsts.CREATE_RECOMMEND_PRODUCT_ERROR,"添加推荐商品失败,id为空。");
        }
        return add;
    }

}

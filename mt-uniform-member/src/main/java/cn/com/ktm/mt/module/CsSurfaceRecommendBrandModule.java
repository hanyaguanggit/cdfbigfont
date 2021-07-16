package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.CsSurfaceRecommendBrand;
import cn.com.ktm.mt.model.CsSurfaceRecommendProduct;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.section.request.CsSurfaceSectionRequest;
import cn.com.ktm.mt.service.CsSurfaceRecommendBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsSurfaceRecommendBrandModule {

    Logger logger = LoggerFactory.getLogger(CsSurfaceRecommendBrandModule.class);
    @Autowired
    private CsSurfaceRecommendBrandService csSurfaceRecommendBrandService;

    public int addRecommendBrand(CsSurfaceSectionRequest csSurfaceSectionRequest){
        CsSurfaceRecommendBrand csSurfaceRecommendBrand = new CsSurfaceRecommendBrand();
        int add = 0;
        csSurfaceRecommendBrand.setSectionId(csSurfaceSectionRequest.getSectionId());
        csSurfaceRecommendBrand.setBrandId(0);
        csSurfaceRecommendBrand.setBrandIds(csSurfaceSectionRequest.getBrandIds());
        try{
           add = csSurfaceRecommendBrandService.addRecommendBrand(csSurfaceRecommendBrand);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("添加推荐品牌失败，推荐品牌实体内容：{}",csSurfaceRecommendBrand);
        }
        if(csSurfaceRecommendBrand.getId() == null){
            Assert.fail(ResponseConsts.CREATE_RECOMMEND_BRAND_ERROR,"添加推荐品牌错误。");
        }
        return add;
    }

}

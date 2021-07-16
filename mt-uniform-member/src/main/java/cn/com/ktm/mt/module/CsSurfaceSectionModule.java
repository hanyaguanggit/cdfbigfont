package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.*;
import cn.com.ktm.mt.model.constant.ResponseCode;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.constant.SectionConsts;
import cn.com.ktm.mt.model.cssitestructure.request.CsSiteStuctureRequest;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.home.response.PictureLinkRes;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.section.request.CsSurfaceSectionRequest;
import cn.com.ktm.mt.model.util.excel.utils.DateUtils;
import cn.com.ktm.mt.model.util.utils.DateUtil;
import cn.com.ktm.mt.model.util.utils.StringUtils;
import cn.com.ktm.mt.service.CsStructurePositionService;
import cn.com.ktm.mt.service.CsSurfacePictureLinkService;
import cn.com.ktm.mt.service.CsSurfaceSectionService;
import cn.com.ktm.mt.service.CsSurfaceSectionTemplateConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class CsSurfaceSectionModule {

    private Logger logger = LoggerFactory.getLogger(CsSurfaceSectionModule.class);

    @Autowired
    private CsStructurePositionService csStructurePositionService;//网站位置

    @Autowired
    private CsSurfaceSectionTemplateConfigService csSurfaceSectionTemplateConfigService;//配置

    @Autowired
    private CsSurfaceSectionService csSurfaceSectionService;//模块

    @Autowired
    private CsPictureLinkModule csPictureLinkModule;//图片链接

    @Autowired
    private CsSurfaceTextLinkModule csSurfaceTextLinkModule;//文字链接

    @Autowired
    private CsSurfaceBestSellerListModule csSurfaceBestSellerListModule;//畅销榜单

    @Autowired
    private CsSurfaceRichTextModule csSurfaceRichTextModule; //富文本

    @Autowired
    private CsSurfaceRecommendBrandModule csSurfaceRecommendBrandModule;//推荐品牌

    @Autowired
    private CsSurfaceRecommendProductModule csSurfaceRecommendProductModule;//推荐商品

    @Autowired
    private CsSurfaceSearchHotWordsModule csSurfaceSearchHotWordsModule;//搜索热词

    /**
     * hyg
     * 添加网站模块
     * @param request
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public OtaResponse addSection(CsSurfaceSectionRequest request) {
        Assert.notNull(request.getSiteStructureId(), ResponseConsts.SITE_STRUCTURE_ID_EMPTY_ERROR, " 网站结构id为空!");
        Assert.notNull(request.getPositionTypeId(),ResponseConsts.POSITION_TYPE_ID_EMPTY_ERROR,"位置类型id为空!");
        //保存位置
        CsStructurePosition csStructurePosition = addPositionModel(request);
        //保存配置信息
        request.setPositionId(csStructurePosition.getId());
        CsSurfaceSectionTemplateConfig csSurfaceSectionTemplateConfig = addConfigModel(request);
        //保存模块
        request.setTemplateConfigId(csSurfaceSectionTemplateConfig.getId());
        CsSurfaceSection csSurfaceSection = addSectionModel(request);

        //根据类型保存具体表信息
        request.setSectionId(csSurfaceSection.getId());
        switch (request.getPositionTypeId()){
            case SectionConsts.PICTURE_LINK:
                csPictureLinkModule.addPictureLinkeModel(request);
                break;
            case SectionConsts.TEXT_LINK:
                csSurfaceTextLinkModule.addTextLinkeModel(request);
                break;
            case SectionConsts.RICH_TEXT:
                csSurfaceRichTextModule.addCsSurfaceRichTextModel(request);
                break;
            case SectionConsts.BEST_SELLER_LIST:
                csSurfaceBestSellerListModule.addBestSellerListModel(request);
                break;
            case SectionConsts.RECOMMEND_PRODUCT:
                csSurfaceRecommendProductModule.addRecommendProduct(request);
                break;
            case SectionConsts.RECOMMEND_BRAND:
                csSurfaceRecommendBrandModule.addRecommendBrand(request);
                break;
            case SectionConsts.SEARCH_HOT_WORDS:
                csSurfaceSearchHotWordsModule.addCsSurfaceSearchHotWordsModel(request);
                break;
            default:
                Assert.fail(ResponseConsts.ERROR,"暂未配置此类型");
                break;
        }
       /* if(request.getPositionTypeId() == SectionConsts.PICTURE_LINK){
            csPictureLinkModule.addPictureLinkeModel(request);
        }else if(request.getPositionTypeId() == SectionConsts.TEXT_LINK){
            csSurfaceTextLinkModule.addTextLinkeModel(request);
        }else if(request.getPositionTypeId() == SectionConsts.RICH_TEXT){
            csSurfaceRichTextModule.addCsSurfaceRichTextModel(request);
        }else if(request.getPositionTypeId() == SectionConsts.BEST_SELLER_LIST){
            csSurfaceBestSellerListModule.addBestSellerListModel(request);
        }else if(request.getPositionTypeId() == SectionConsts.RECOMMEND_PRODUCT){
            csSurfaceRecommendProductModule.addRecommendProduct(request);
        }else if(request.getPositionTypeId() == SectionConsts.RECOMMEND_BRAND){
            csSurfaceRecommendBrandModule.addRecommendBrand(request);
        }else if(request.getPositionTypeId() == SectionConsts.SEARCH_HOT_WORDS){
            csSurfaceSearchHotWordsModule.addCsSurfaceSearchHotWordsModel(request);
        }else{
            Assert.fail(ResponseConsts.ERROR,"暂未配置此类型");
        }*/
        OtaResponse response = new OtaResponse();
        response.setCode(ResponseConsts.SUCCESS);
        response.setSectionId(csSurfaceSection.getId());
        response.setDescribe("添加模块成功!");
        return response;
    }


    /**
     * hyg
     * 创建模块位置
     * @param request
     * @return
     */
    public  CsStructurePosition addPositionModel (CsSurfaceSectionRequest request){
        CsStructurePosition csStructurePosition = new CsStructurePosition();
        csStructurePosition.setName(request.getSectionName());
        csStructurePosition.setPositiontypeId(request.getPositionTypeId());
        csStructurePosition.setSiteStructureId(request.getSiteStructureId());
        csStructurePosition.setEternal(Byte.valueOf("0"));
        csStructurePosition.setRemark("");
        try {
            csStructurePositionService.addCsStructurePosition(csStructurePosition);
        }catch (Exception e){
          logger.error("创建模块位置id-模块位置主键约束重复！,positionId={}",csStructurePosition.getId());
           e.printStackTrace();
        }
        if (csStructurePosition.getId() == null)
            Assert.fail(ResponseConsts.CREATE_POSITION_ERROR, "创建模块位置id-位置id错误");
        return csStructurePosition;
    }

    /**
     * hyg
     * 创建模块配置信息
     * @param request
     * @return
     */
    public CsSurfaceSectionTemplateConfig addConfigModel(CsSurfaceSectionRequest request){
        CsSurfaceSectionTemplateConfig csSurfaceSectionTemplateConfig = new CsSurfaceSectionTemplateConfig();
        csSurfaceSectionTemplateConfig.setPositionId(request.getPositionId());
        csSurfaceSectionTemplateConfig.setPositiontypeId(request.getPositionTypeId());
        csSurfaceSectionTemplateConfig.setTemplateId(request.getTemplateId());
        csSurfaceSectionTemplateConfig.setSitestructureId(request.getSiteStructureId());
        String tabName = "";
        if(request.getPositionTypeId() == SectionConsts.PICTURE_LINK ){
              tabName = SectionConsts.PICTURE_LINK_TAB_NAME;
        }
        else if(request.getPositionTypeId() == SectionConsts.RECOMMEND_BRAND){
              tabName = SectionConsts.RECOMMEND_BRAND_TAB_NAME;
        }
        else if(request.getPositionTypeId() == SectionConsts.RECOMMEND_PRODUCT){
            tabName = SectionConsts.RECOMMEND_PRODUCT_TAB_NAME;
        }
        else if(request.getPositionTypeId() ==SectionConsts.RECOMMENG_ARTICLE){
            tabName = SectionConsts.RECOMMENG_ARTICLE_TAB_NAME;
        }
        else if(request.getPositionTypeId() == SectionConsts.RICH_TEXT){
            tabName = SectionConsts.RICH_TEXT_TAB_NAME;
        }
        else if(request.getPositionTypeId() == SectionConsts.SHARE_ACTIVITY){
            tabName = SectionConsts.SHARE_ACTIVITY_TAB_NAME;
        }
        else if(request.getPositionTypeId() == SectionConsts.TEXT_LINK){
            tabName = SectionConsts.TEXT_LINK_TAB_NAME;
        }else {
            tabName = "未知";
        }
        csSurfaceSectionTemplateConfig.setLinkTabname(tabName);
        try{
            csSurfaceSectionTemplateConfigService.addCsSurfaceSectionTemplateConfig(csSurfaceSectionTemplateConfig);
        }catch (Exception e){
          e.printStackTrace();
            Assert.fail(ResponseConsts.CREATE_POSITION_ERROR, "创建模块配置id错误");
            logger.error("创建模块配置信息异常，templateConfigId={}",csSurfaceSectionTemplateConfig.getId());
        }
        if(csSurfaceSectionTemplateConfig.getId() == null){
            Assert.fail(ResponseConsts.CREATE_POSITION_ERROR, "创建模块配置id错误");
        }
        return csSurfaceSectionTemplateConfig;
    }

    /**
     * hyg
     * 创建模块
     * @param request
     * @return
     */
    public CsSurfaceSection addSectionModel(CsSurfaceSectionRequest request){
        CsSurfaceSection csSurfaceSection = new CsSurfaceSection();
        csSurfaceSection.setTemplateId(request.getTemplateId());
        csSurfaceSection.setTemplateConfigId(request.getTemplateConfigId());
        csSurfaceSection.setSiteStructureId(request.getSiteStructureId());
        csSurfaceSection.setPositionId(request.getPositionId());
        csSurfaceSection.setPositionTypeId(request.getPositionTypeId());
        csSurfaceSection.setName(request.getSectionName());
        csSurfaceSection.setAdvertiser(0);
        csSurfaceSection.setStatus((byte) request.getStatus());
        csSurfaceSection.setTarget(request.getTarget() == 1 ? true :false);
        Date fromDate = null;
        Date thruDate = null;
        if(!StringUtils.isBlank(request.getFromTime())){
            try {
                fromDate = DateUtils.formatDate(request.getFromTime(),DateUtils.DATE_FORMAT_DEFAULT);
            } catch (Exception e) {
                Assert.fail(ResponseConsts.PARAM_ERROR,"生效时间格式有误。");
                e.printStackTrace();
            }
        }
        if(!StringUtils.isBlank(request.getThruTime())){
            try {
                thruDate = DateUtils.formatDate(request.getThruTime(),DateUtils.DATE_FORMAT_DEFAULT);
            } catch (Exception e) {
                Assert.fail(ResponseConsts.PARAM_ERROR,"失效时间格式有误。");
                e.printStackTrace();
            }
        }
        csSurfaceSection.setFromTime(fromDate == null ? null : fromDate);
        csSurfaceSection.setThruTime(thruDate == null ? null : thruDate);
        csSurfaceSection.setRemark("");
        csSurfaceSection.setSequence((short)request.getSequence());//必填
        csSurfaceSection.setCreator(request.getCreator());//必填
        csSurfaceSection.setCreateTime(new Date());//必填
        csSurfaceSection.setLastModifiedUser(0);//必填
        csSurfaceSection.setLastModifiedTime(new Date());
        csSurfaceSection.setIsPreload(false);//是否预加载
        csSurfaceSection.setShopId(request.getShopId());//必填
        try{
            csSurfaceSectionService.addCsSurfaceSection(csSurfaceSection);
        }catch (Exception e){
            logger.error("创建网站模块异常,模块id={}",csSurfaceSection.getId());
            e.printStackTrace();
        }
        if(csSurfaceSection.getId() == null){
            Assert.fail(ResponseConsts.CREATE_SECTION_ERROE,"创建网站模块错误。");
        }
         return csSurfaceSection;
    }


}

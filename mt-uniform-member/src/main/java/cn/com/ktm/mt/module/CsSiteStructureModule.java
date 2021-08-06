package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.CsSiteStructure;
import cn.com.ktm.mt.model.constant.SectionConsts;
import cn.com.ktm.mt.model.cssitestructure.request.CsSiteStuctureRequest;
import cn.com.ktm.mt.model.cssitestructure.response.CsSiteStuctureHomeResponseVo;
import cn.com.ktm.mt.model.cssitestructure.response.CsSiteStuctureListRes;
import cn.com.ktm.mt.model.cssitestructure.response.CsSiteStuctureResponse;
import cn.com.ktm.mt.model.cssitestructure.response.CsSiteStuctureResponseBody;
import cn.com.ktm.mt.model.home.request.SiteStructureHomeRequest;
import cn.com.ktm.mt.model.home.response.*;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.service.CsProductService;
import cn.com.ktm.mt.service.CsSiteStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public class CsSiteStructureModule {
    @Autowired
    private CsSiteStructureService csSiteStructureService;

    @Autowired
    private CsProductService csProductService;

    Logger logger = LoggerFactory.getLogger(CsSecurityUserModule.class);

    public OtaResponse getInfo(CsSiteStuctureRequest request) {
        Assert.notNull(request.getId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, " id为空");
        CsSiteStructure csSiteStructure;
        csSiteStructure = csSiteStructureService.findCsSiteStructureById(Integer.parseInt(request.getId()));
        CsSiteStuctureResponse response = new CsSiteStuctureResponse();
        CsSiteStuctureResponseBody responseBody = new CsSiteStuctureResponseBody();
        responseBody.setId(csSiteStructure.getId());
        responseBody.setName(csSiteStructure.getName());
        responseBody.setParentId(csSiteStructure.getParentId());
        responseBody.setStatus(csSiteStructure.getStatus());
        responseBody.setUrl(csSiteStructure.getUrl());
        response.setBody(responseBody);
        response.setCode(ResponseConsts.SUCCESS);
        response.setDescribe("success");
        logger.info("获取网站信息：{}", responseBody);
        return response;
    }

    /**
     * 获取首页内容请求
     *
     * @param request
     * @return
     */
    public OtaResponse getHomeInfo(SiteStructureHomeRequest request) {
        Assert.notNull(request.getBody().getTemplateId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, " 商铺id为空");
        Assert.notEmpty(request.getBody().getSiteStructureIdList(), ResponseConsts.PARAM_ERROR, "网站结构id缺失。");
        Map<Integer, List<CsSiteStuctureHomeResponseVo>> homepagemap;
        HomePageInfoResponse homePageInfoResponse = new HomePageInfoResponse();
        List<PictureLinkRes> pictureLinkResList = new ArrayList<>();//图片链接
        List<RecommendProductRes> recommendProductResList = new ArrayList<>();//推荐商品
        List<BestSellerListRes> bestSellerListResList = new ArrayList<>();//畅销榜单
        List<IndexFloorRes> indexFloorResList = new ArrayList<>();//首页楼层
        List<RichTextRes> richTextResList = new ArrayList<>();//富文本
        List<SearchHotWordsRes> searchHotWordsResList = new ArrayList<>();//搜索热词
        List<TextLinkRes> textLinkResList = new ArrayList<>();//文字链接
        Set<Integer> siteStructIdList = new HashSet<>();//首页网站id所属的网站结构 不能重复
        // siteStructIdList.add(request.getBody().getSiteStructureIdList().get(0));//不包含顶级id

        try {
            //虽然设计的是可以传多个网站id，但是暂时使用只传顶层的网站结构--45100，通过顶层id查询下级，如果下级中包含了1级网站结构，继续递归查询1级id下属的网站结构，直到找完为止。
            List<CsSiteStructure> csList = csSiteStructureService.findCsSiteStructureByParentId(request.getBody().getSiteStructureIdList().get(0));
            Assert.notEmpty(csList, ResponseConsts.SITE_STRUCTURE_ID_EMPTY_ERROR, "网站结构为空。");
            //1、循环检验是否包含1级id，若包含继续查询下级。
            siteStructIdList = filterSiteStructureId(csList);
            logger.info("首页所属完整网站结构id集合：{}", siteStructIdList);
            //2.根据查找到的所有2级网站结构查询模块。
            List<CsSiteStuctureHomeResponseVo> csSiteStructureList = csSiteStructureService.showSiteStructureHome(siteStructIdList, request.getBody().getTemplateId());
            logger.info("获取完整首页信息,集合总数为:{},详情如下:{},", csSiteStructureList.size(), csSiteStructureList);
            Assert.notEmpty(csSiteStructureList, ResponseConsts.CS_SITE_STRUCTURE_SECTION_EMPTY, "网站结构所属模块为空。");
            //根据网站结构id分组，找出哪个结构下都分配了哪些模块。
            homepagemap = csSiteStructureList.stream().collect(Collectors.groupingBy(CsSiteStuctureHomeResponseVo::getSiteStructureId));
            //根据分组后的具体模块查询，例如查询轮播图。
            //外层循环网站结构id,内层循环网站结构下的模块。找出网站结构下的具体模块的具体信息，进行封装返回。
            siteStructIdList.forEach(siteStructure -> {
                if (homepagemap.get(siteStructure) != null) {//可能有些网站结构下没有配置模块，所以加入判断。
                    //网站结构下的模块
                    homepagemap.get(siteStructure).forEach(home -> {
                        if (home.getSectionId() != null && home.getLinkTab() != null) {
                            HomePageResponseBody homePageResponseBody = csSiteStructureService.findHomeRecommendDto(home.getSectionId(), home.getLinkTab());
                            if (homePageResponseBody != null) {
                                homePageResponseBody.setSiteStructureId(home.getSiteStructureId());
                                homePageResponseBody.setPositionTypeId(home.getPositionTypeId());
                                if (SectionConsts.BEST_SELLER_LIST_TAB_NAME.equalsIgnoreCase(home.getLinkTab())) {
                                    bestSellerListResList.add(createBestSellerRes(homePageResponseBody));
                                }
                                if (SectionConsts.TEXT_LINK_TAB_NAME.equalsIgnoreCase(home.getLinkTab())) {
                                    textLinkResList.add(createTextLinkRes(homePageResponseBody));
                                }
                                if (SectionConsts.RICH_TEXT_TAB_NAME.equalsIgnoreCase(home.getLinkTab())) {
                                    richTextResList.add(createRichText(homePageResponseBody));
                                }
                                if (SectionConsts.SEARCH_HOT_WORDS_TAB_NAME.equalsIgnoreCase(home.getLinkTab())) {
                                    searchHotWordsResList.add(createHotWordsRes(homePageResponseBody));
                                }
                                if (SectionConsts.INDEX_FLOOR_TAB_NAME.equalsIgnoreCase(home.getLinkTab())) {
                                    indexFloorResList.add(createIndexFloorRes(homePageResponseBody));
                                }
                                if (SectionConsts.PICTURE_LINK_TAB_NAME.equalsIgnoreCase(home.getLinkTab())) {
                                    pictureLinkResList.add(createPictureLinkRes(homePageResponseBody));
                                }
                                ///推荐商品
                                if (SectionConsts.RECOMMEND_PRODUCT_TAB_NAME.equalsIgnoreCase(home.getLinkTab())) {
                                    recommendProductResList.add(createRecommendProduct(homePageResponseBody));
                                }
                            }
                        }
                    });
                }
            });
            HomePageInfoResponseBody homePageInfoResponseBody = new HomePageInfoResponseBody();
            homePageInfoResponseBody.setPictureLinkResList(pictureLinkResList);
            homePageInfoResponseBody.setRecommendProductResList(recommendProductResList);
            homePageInfoResponseBody.setBestSellerListResList(bestSellerListResList);
            homePageInfoResponseBody.setIndexFloorResList(indexFloorResList);
            homePageInfoResponseBody.setRichTextResList(richTextResList);
            homePageInfoResponseBody.setSearchHotWordsResList(searchHotWordsResList);
            homePageInfoResponseBody.setTextLinkResList(textLinkResList);
            int totalsize = pictureLinkResList.size() + recommendProductResList.size() + bestSellerListResList.size() + indexFloorResList.size() + richTextResList.size() + searchHotWordsResList.size() + textLinkResList.size();
            logger.info("整理数据之前的总数为：{}，整理数据之后的总数为：{}", csSiteStructureList.size(), totalsize);
            homePageInfoResponse.setBody(homePageInfoResponseBody);
            homePageInfoResponse.setCode(ResponseConsts.SUCCESS);
            homePageInfoResponse.setDescribe("success");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Assert.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION);
        }
        return homePageInfoResponse;
    }

    /**
     * hyg
     * 根据parentId查询子网站结构集合
     * @param request
     * @return
     */
    public OtaResponse findCsSiteStructureByParentId(CsSiteStuctureRequest request) {
        Assert.notNull(request.getId(), ResponseConsts.MEMBER_PARAM_CONTACT_ERROR, " id为空");
        CsSiteStuctureListRes response = new CsSiteStuctureListRes();
        List<CsSiteStuctureResponseBody> csList = new ArrayList<>();
        List<CsSiteStructure> csSiteStructureList = csSiteStructureService.findCsSiteStructureByParentId(Integer.parseInt(request.getId()));
        csSiteStructureList.forEach(csSiteStructure -> {
            CsSiteStuctureResponseBody responseBody = new CsSiteStuctureResponseBody();
            responseBody.setId(csSiteStructure.getId());
            responseBody.setName(csSiteStructure.getName());
            responseBody.setParentId(csSiteStructure.getParentId());
            responseBody.setStatus(csSiteStructure.getStatus());
            responseBody.setUrl(csSiteStructure.getUrl());
            responseBody.setType(csSiteStructure.getType());
            csList.add(responseBody);
        });
        response.setCsSiteStuctureResponseBodyList(csList);
        response.setCode(ResponseConsts.SUCCESS);
        return response;
    }

    /**
     * hyg
     * 查询网站结构下所有子节点id
     * @param list
     * @return siteStructIdList
     */
    public Set<Integer> filterSiteStructureId(List<CsSiteStructure> list) {
        Set<Integer> siteStructIdList = new HashSet<>();
        CopyOnWriteArrayList<CsSiteStructure> newlist = new CopyOnWriteArrayList();//防止在循环过程中，如果集合发生了改变，会抛异常。
        newlist.addAll(list);
        newlist.forEach(cs1 ->{
            siteStructIdList.add(cs1.getId());
            if (cs1.getType() == 1) {//1级
                List<CsSiteStructure> childs = csSiteStructureService.findCsSiteStructureByParentId(cs1.getId());
                logger.info("下级id为：{}", childs);
                if (childs.size() > 0) {
                    //如果下层还查到有1级id添加到csList继续循环
                    childs.forEach(cs2->{
                        if (cs2.getType() == 1) {
                            newlist.add(cs2);
                        }
                    });
                    siteStructIdList.addAll(newlist.stream().filter(o -> o.getType() == 2).map(CsSiteStructure::getId).collect(Collectors.toList()));//把不包含一级网站id的添加到集合中
                }
            }
        });
        return siteStructIdList.size() > 0 ? siteStructIdList : Collections.emptySet();
    }

    /**
     * hyg
     * 创建畅销榜单返回体信息
     *
     * @param homePageResponseBody
     * @return bestSellerListRes
     */
    public BestSellerListRes createBestSellerRes(HomePageResponseBody homePageResponseBody) {
        //可以单独提出来封装一个方法
        BestSellerListRes bestSellerListRes = new BestSellerListRes();
        bestSellerListRes.setId(homePageResponseBody.getId());
        bestSellerListRes.setPicId(homePageResponseBody.getPicId());
        bestSellerListRes.setDisplayType(homePageResponseBody.getDisplayType());
        bestSellerListRes.setGoodsIds(homePageResponseBody.getGoodsIdsList());
        bestSellerListRes.setSectionId(homePageResponseBody.getSectionId());
        bestSellerListRes.setSiteStructureId(homePageResponseBody.getSiteStructureId());
        bestSellerListRes.setPositionTypeId(homePageResponseBody.getPositionTypeId());
        return bestSellerListRes;
    }

    /**
     * hyg
     * 创建首页非楼层返回体
     *
     * @param homePageResponseBody
     * @return indexFloorRes
     */
    public IndexFloorRes createIndexFloorRes(HomePageResponseBody homePageResponseBody) {
        IndexFloorRes indexFloorRes = new IndexFloorRes();
        indexFloorRes.setId(homePageResponseBody.getId());
        indexFloorRes.setSectionId(homePageResponseBody.getSectionId());
        indexFloorRes.setGoodsIds(homePageResponseBody.getGoodsIds());
        indexFloorRes.setGoodsIdsList(homePageResponseBody.getGoodsIdsList());
        indexFloorRes.setLinkType(homePageResponseBody.getLinkType());
        indexFloorRes.setLinkUrl(homePageResponseBody.getLinkUrl());
        indexFloorRes.setSiteStructureId(homePageResponseBody.getSiteStructureId());
        indexFloorRes.setPositionTypeId(homePageResponseBody.getPositionTypeId());
        return indexFloorRes;
    }

    /**
     * hyg
     * 创建图片
     *
     * @param homePageResponseBody
     * @return pictureLinkRes
     */
    public PictureLinkRes createPictureLinkRes(HomePageResponseBody homePageResponseBody) {
        PictureLinkRes pictureLinkRes = new PictureLinkRes();
        pictureLinkRes.setId(homePageResponseBody.getId());
        pictureLinkRes.setPicture(homePageResponseBody.getPicture());
        pictureLinkRes.setPictureUrl(homePageResponseBody.getUrl());
        pictureLinkRes.setSectionId(homePageResponseBody.getSectionId());
        pictureLinkRes.setSiteStructureId(homePageResponseBody.getSiteStructureId());
        pictureLinkRes.setPositionTypeId(homePageResponseBody.getPositionTypeId());
        return pictureLinkRes;
    }

    /**
     * hyg
     * 创建首页推荐商品返回体
     * @param homePageResponseBody
     * @return
     */
    public RecommendProductRes createRecommendProduct(HomePageResponseBody homePageResponseBody) {
        RecommendProductRes recommendProductRes = new RecommendProductRes();
        recommendProductRes.setId(homePageResponseBody.getId());
        recommendProductRes.setSectionId(homePageResponseBody.getSectionId());
        recommendProductRes.setProductLinks(homePageResponseBody.getProductLinkList());
        recommendProductRes.setSiteStructureId(homePageResponseBody.getSiteStructureId());
        recommendProductRes.setPositionTypeId(homePageResponseBody.getPositionTypeId());
        return recommendProductRes;
    }

    /**
     * hyg
     *富文本
     * @param homePageResponseBody
     * @return
     */
    public RichTextRes createRichText(HomePageResponseBody homePageResponseBody){
       RichTextRes richTextRes = new RichTextRes();
       richTextRes.setId(homePageResponseBody.getId());
       richTextRes.setSectionId(homePageResponseBody.getSectionId());
       richTextRes.setSiteStructureId(homePageResponseBody.getSiteStructureId());
       richTextRes.setPositionTypeId(homePageResponseBody.getPositionTypeId());
       richTextRes.setRichContent(homePageResponseBody.getContent());
        return richTextRes;
    }

    /**
     * hyg
     * 创建搜索热词
     * @param homePageResponseBody
     * @return searchHotWordsRes
     */
    public SearchHotWordsRes createHotWordsRes(HomePageResponseBody homePageResponseBody ){
        SearchHotWordsRes searchHotWordsRes = new SearchHotWordsRes();
        searchHotWordsRes.setId(homePageResponseBody.getId());
        searchHotWordsRes.setSectionId(homePageResponseBody.getSectionId());
        searchHotWordsRes.setSiteStructureId(homePageResponseBody.getSiteStructureId());
        searchHotWordsRes.setPositionTypeId(homePageResponseBody.getPositionTypeId());
        searchHotWordsRes.setSearchText(homePageResponseBody.getTitle());
        searchHotWordsRes.setRecommend(homePageResponseBody.getRecommend());
        searchHotWordsRes.setLinkType(homePageResponseBody.getLinkType());
        searchHotWordsRes.setLinkUrl(homePageResponseBody.getLinkUrl());
        return searchHotWordsRes;
    }

    /**
     * hyg
     * 文字链接
     * @param homePageResponseBody
     * @return textLinkRes
     */
    public TextLinkRes createTextLinkRes(HomePageResponseBody homePageResponseBody){
        TextLinkRes textLinkRes = new TextLinkRes();
        textLinkRes.setId(homePageResponseBody.getId());
        textLinkRes.setSectionId(homePageResponseBody.getSectionId());
        textLinkRes.setSiteStructureId(homePageResponseBody.getSiteStructureId());
        textLinkRes.setPositionTypeId(homePageResponseBody.getPositionTypeId());
        textLinkRes.setTextName(homePageResponseBody.getName());
        textLinkRes.setTextUrl(homePageResponseBody.getUrl());
        return textLinkRes;
    }
}

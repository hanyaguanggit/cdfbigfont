package cn.com.ktm.mt.model.home.response;

import cn.com.ktm.mt.model.message.Body;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 首页信息相应实体
 */

public class HomePageResponseBody implements Body {

    private Integer id;
    private Integer sectionId; //模块id
    private String url; //跳转路径
    private Integer siteStructureId;//网站结构id
    private Integer positionTypeId;//位置id

    //畅销榜单   cs_surface_best_seller_list
    private Integer picId;//图片
    private String goodsIds;//商品id集合
    private Integer displayType;//展示类型 1：红色
    private List<Integer> goodsIdsList;


    //图片链接  cs_surface_picturelink
    private Integer picture; //图片


    //推荐文章 cs_surface_recommend_article
    private Integer articleId;// 文章id
    private String articleLink; //文章链接

    //推荐品牌 cs_surface_recommend_brand
    private String brandIds; //品牌id集合
    private List<Integer> brandIdsList;

    //推荐分类 cs_surface_recommend_category
    private String categoryIds; //分类id集合
    private List<Integer> categoryIdsList;

    //推荐产品 cs_surface_recommend_product
    private String productLink; //商品id集合
    private List<Integer> productLinkList;


    //富文本信息 cs_surface_richtext
    private String content;

    //文字链接 cs_surface_textlink
    private String name; //文字名称


    //搜索热词 cs_surface_search_hot_words
    private Integer recommend;//是否推荐搜索词 0：否 1：是
    private String title; //标题
    private Integer linkType;//链接类型 0=搜索词 1=自定义链接
    private String  targetUrl; //热词跳转URL

    //首页楼层 cs_surface_index_floor
    private String linkUrl;//首页楼层的链接地址

    public HomePageResponseBody(Integer id, Integer sectionId, String url, Integer siteStructureId, Integer positionTypeId, Integer picId, String goodsIds, Integer displayType, List<Integer> goodsIdsList, Integer picture, Integer articleId, String articleLink, String brandIds, List<Integer> brandIdsList, String categoryIds, List<Integer> categoryIdsList, String productLink, List<Integer> productLinkList, String content, String name, Integer recommend, String title, Integer linkType, String targetUrl, String linkUrl) {
        this.id = id;
        this.sectionId = sectionId;
        this.url = url;
        this.siteStructureId = siteStructureId;
        this.positionTypeId = positionTypeId;
        this.picId = picId;
        this.goodsIds = goodsIds;
        this.displayType = displayType;
        this.goodsIdsList = goodsIdsList;
        this.picture = picture;
        this.articleId = articleId;
        this.articleLink = articleLink;
        this.brandIds = brandIds;
        this.brandIdsList = brandIdsList;
        this.categoryIds = categoryIds;
        this.categoryIdsList = categoryIdsList;
        this.productLink = productLink;
        this.productLinkList = productLinkList;
        this.content = content;
        this.name = name;
        this.recommend = recommend;
        this.title = title;
        this.linkType = linkType;
        this.targetUrl = targetUrl;
        this.linkUrl = linkUrl;
    }

    public HomePageResponseBody() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPicId() {
        return picId;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
        this.goodsIdsList = addList(goodsIds);
    }

    public Integer getDisplayType() {
        return displayType;
    }

    public void setDisplayType(Integer displayType) {
        this.displayType = displayType;
    }

    public List<Integer> getGoodsIdsList() {
        return goodsIdsList;
    }

    public void setGoodsIdsList(List<Integer> goodsIdsList) {
        this.goodsIdsList = goodsIdsList;
    }

    public Integer getPicture() {
        return picture;
    }

    public void setPicture(Integer picture) {
        this.picture = picture;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public void setArticleLink(String articleLink) {
        this.articleLink = articleLink;
    }

    public String getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(String brandIds) {
        this.brandIds = brandIds;
        this.brandIdsList = addList(brandIds);
    }

    public List<Integer> getBrandIdsList() {
        return brandIdsList;
    }

    public void setBrandIdsList(List<Integer> brandIdsList) {
        this.brandIdsList = brandIdsList;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
        this.categoryIdsList = addList(categoryIds);
    }

    public List<Integer> getCategoryIdsList() {
        return categoryIdsList;
    }

    public void setCategoryIdsList(List<Integer> categoryIdsList) {
        this.categoryIdsList = categoryIdsList;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
        this.productLinkList = addList(productLink);
    }

    public List<Integer> getProductLinkList() {
        return productLinkList;
    }

    public void setProductLinkList(List<Integer> productLinkList) {

        this.productLinkList = productLinkList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLinkType() {
        return linkType;
    }

    public void setLinkType(Integer linkType) {
        this.linkType = linkType;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getSiteStructureId() {
        return siteStructureId;
    }

    public void setSiteStructureId(Integer siteStructureId) {
        this.siteStructureId = siteStructureId;
    }

    public Integer getPositionTypeId() {
        return positionTypeId;
    }

    public void setPositionTypeId(Integer positionTypeId) {
        this.positionTypeId = positionTypeId;
    }

    public  List<Integer>  addList(String strids)
    {
        List<Integer> list = new ArrayList<>();
        if(StringUtils.isNotEmpty(strids)){
            String[] idsAray = strids.split(",");
            if(idsAray.length > 0){
                for (String id: idsAray) {
                    list.add(Integer.parseInt(id));
                }
            }
        }
        return list == null ? Collections.emptyList() : list ;
    }
    @Override
    public void valid() {

    }
}

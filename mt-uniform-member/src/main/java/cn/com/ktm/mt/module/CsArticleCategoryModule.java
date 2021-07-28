package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.article.request.ArticleCategoryReqVo;
import cn.com.ktm.mt.model.article.request.SaveArticleCategoryReqVo;
import cn.com.ktm.mt.model.article.response.ArticleCategoryResVoBody;
import cn.com.ktm.mt.model.article.response.ArticleCategoryResVoBodyList;
import cn.com.ktm.mt.model.bean.CsArticleCategory;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.OtaRequest;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.util.utils.DateUtil;
import cn.com.ktm.mt.service.CsArticleCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CsArticleCategoryModule {
    Logger logger = LoggerFactory.getLogger(CsArticleCategoryModule.class);
    @Autowired
    private CsArticleCategoryService csArticleCategoryService;

public OtaResponse selectArticleCategory(ArticleCategoryReqVo request){
    Assert.notNull(request.getShopId(), ResponseConsts.PARAM_ERROR,"店铺id为空。");
    OtaResponse<ArticleCategoryResVoBodyList> response = new OtaResponse();
    List<ArticleCategoryResVoBody> articleCategoryResVoBodyList = new ArrayList<>();
    List<CsArticleCategory>  aclist0= csArticleCategoryService.selectArticleCategory(request.getShopId());
    if(aclist0.size() > 0){
        aclist0.forEach(a ->{
            ArticleCategoryResVoBody articleCategoryResVoBody = new ArticleCategoryResVoBody();
            articleCategoryResVoBody.setId(a.getId());
            articleCategoryResVoBody.setParentId(a.getParentId());
            articleCategoryResVoBody.setName(a.getName());
            articleCategoryResVoBody.setNameEN(a.getNameEN());
            articleCategoryResVoBody.setSequence((int)a.getSequence());
            articleCategoryResVoBody.setStatus((int)a.getStatus());
            String createTime = "";
            if(null != a.getCreateTime()){
                SimpleDateFormat sdf=new SimpleDateFormat(DateUtil.format1);
                try {
                    createTime = sdf.format(a.getCreateTime());
                } catch (Exception e) {
                    e.printStackTrace();
                    createTime ="";
                }
            }
            articleCategoryResVoBody.setCreateTime(createTime);
            String lastModifiedTime ="";
            articleCategoryResVoBody.setCreator(a.getCreator());
            if(null != a.getLastModifiedTime()){
                SimpleDateFormat sdf=new SimpleDateFormat(DateUtil.format1);
                try {
                    lastModifiedTime = sdf.format(a.getLastModifiedTime());
                } catch (Exception e) {
                    e.printStackTrace();
                    lastModifiedTime ="";
                }
            }
            articleCategoryResVoBody.setLastModifiedTime(lastModifiedTime);
            articleCategoryResVoBody.setLastModifiedUser(a.getLastModifiedUser());
            articleCategoryResVoBody.setShopId(a.getShopId());
            articleCategoryResVoBodyList.add(articleCategoryResVoBody);
        });
        ArticleCategoryResVoBodyList arlist1 = new ArticleCategoryResVoBodyList();
        arlist1.setArticleCategoryResVoBodyList(articleCategoryResVoBodyList);
        response.setBody(arlist1);
        response.setCode(ResponseConsts.SUCCESS);
        response.setDescribe("查询成功。");
    }else{
        response.setCode(ResponseConsts.SUCCESS);
        response.setDescribe("无数据。");
    }
    return response;
}

    /**
     * 保存文章栏目
     * @param request
     * @return
     */
    public OtaResponse saveArticleCategory(SaveArticleCategoryReqVo request){
      OtaResponse response = new OtaResponse();
      request.getBody().valid();
      CsArticleCategory csArticleCategory = new CsArticleCategory();
      csArticleCategory.setParentId(request.getBody().getParentId());
      csArticleCategory.setName(request.getBody().getName());
      csArticleCategory.setNameEN(request.getBody().getNameEN());
      csArticleCategory.setStatus(request.getBody().getStatus().byteValue());
      csArticleCategory.setSequence(request.getBody().getSequence().shortValue());
      csArticleCategory.setCreateTime(new Date());
      csArticleCategory.setCreator(request.getBody().getCreator());
      csArticleCategory.setLastModifiedUser(request.getBody().getCreator());
      csArticleCategory.setLastModifiedTime(new Date());
      csArticleCategory.setShopId(request.getShopId());
        boolean save = false;
        try {
            save = csArticleCategoryService.saveArtcleCategory(csArticleCategory);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("保存失败，保存对象：{}",csArticleCategory);
        }
        if(save){
          response.setCode(ResponseConsts.SUCCESS);
          response.setDescribe("保存成功");
      }else{
          response.setCode(ResponseConsts.ERROR);
          response.setDescribe("保存失败。");
      }
      return response;
    }
}

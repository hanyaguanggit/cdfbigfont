package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.article.request.ArticleReqVo;
import cn.com.ktm.mt.model.article.response.ArticleResVoBody;
import cn.com.ktm.mt.model.article.response.ArticleResVoBodyList;
import cn.com.ktm.mt.model.bean.CsArticle;
import cn.com.ktm.mt.model.bean.CsArticleWithBLOBs;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.util.utils.DateUtil;
import cn.com.ktm.mt.model.util.utils.DateUtils;
import cn.com.ktm.mt.service.CsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsArticleModule {
    @Autowired
    private CsArticleService csArticleService;

    public OtaResponse<ArticleResVoBodyList> selectCsArticle(ArticleReqVo request){
     request.getBody().valid();
     List<ArticleResVoBody> aList = new ArrayList<>();
     OtaResponse<ArticleResVoBodyList> response = new OtaResponse<>();
     ArticleResVoBodyList articleResVoBodyList = new ArticleResVoBodyList();
     List<CsArticleWithBLOBs> csArticleList = csArticleService.selectByCategoryId(request.getBody().getCategoryId());
    if(csArticleList.size() > 0){
        csArticleList.forEach(a ->{
            ArticleResVoBody articleResVoBody = new ArticleResVoBody();
            articleResVoBody.setArticleId(a.getId());
            articleResVoBody.setCategoryId(a.getCategoryId());
            articleResVoBody.setArticleName(a.getName());
            articleResVoBody.setArticleNameEN(a.getNameEN());
            articleResVoBody.setAuthorName(a.getAuthorName());
            articleResVoBody.setPicId(a.getPicId());
            articleResVoBody.setInfo(a.getInfo());//标题
            articleResVoBody.setContent(a.getContent());//内容
            String publishTime = "";
            if(a.getPublishTime() != null){
                SimpleDateFormat sdf=new SimpleDateFormat(DateUtil.format1);
                publishTime = sdf.format(a.getPublishTime());
            }
            articleResVoBody.setPublishTime(publishTime);//发布时间
            articleResVoBody.setStatus((int)a.getStatus());
            articleResVoBody.setSequence((int)a.getSequence());
            articleResVoBody.setVisitCount(a.getVisitCount());
            aList.add(articleResVoBody);
        });
    }
     articleResVoBodyList.setArticleList(aList);
     response.setBody(articleResVoBodyList);
     response.setCode(ResponseConsts.SUCCESS);
     response.setDescribe("查询成功。");
     return response;
    }
}

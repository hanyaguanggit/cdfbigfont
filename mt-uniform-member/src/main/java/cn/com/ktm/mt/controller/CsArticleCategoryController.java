package cn.com.ktm.mt.controller;

import cn.com.ktm.mt.model.article.request.ArticleCategoryReqVo;
import cn.com.ktm.mt.model.article.request.ArticleReqVo;
import cn.com.ktm.mt.model.article.request.SaveArticleCategoryReqVo;
import cn.com.ktm.mt.model.exception.AssertError;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.module.CsArticleCategoryModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsArticleCategoryController {
    @Autowired
    private CsArticleCategoryModule csArticleCategoryModule;

    /**
     * hyg
     * 后台--查询文章栏目
     * @param request
     * @return
     */
    @PostMapping(value = "admin/selectArticleCategory", consumes = "application/json")
    public OtaResponse selectArticleCategoryList(@RequestBody ArticleCategoryReqVo request) {
        OtaResponse process = new OtaResponse<>();
        try {
            process = csArticleCategoryModule.selectArticleCategory(request);
        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(),null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return process;
    }

    /**
     * hyg
     * 后台--保存文章栏目
     * @param request
     * @return
     */
    @PostMapping(value = "admin/saveArticleCategory", consumes = "application/json")
    public OtaResponse saveArticleCategory(@RequestBody SaveArticleCategoryReqVo request) {
        OtaResponse process = new OtaResponse<>();
        try {
            process = csArticleCategoryModule.saveArticleCategory(request);
        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(),null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return process;
    }

}

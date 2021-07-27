package cn.com.ktm.mt.controller;

import cn.com.ktm.mt.model.article.request.ArticleReqVo;
import cn.com.ktm.mt.model.article.response.ArticleResVo;
import cn.com.ktm.mt.model.exception.AssertError;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.security.request.user.EnabledReqVo;
import cn.com.ktm.mt.module.CsArticleModule;
import cn.com.ktm.mt.service.CsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsArticleController {
    @Autowired
    private CsArticleModule csArticleModule;

    /**
     * hyg
     * 后台--查询文章
     * @param request
     * @return
     */
    @PostMapping(value = "admin/selectArticleList", consumes = "application/json")
    public OtaResponse selectArticleList(@RequestBody ArticleReqVo request) {
        OtaResponse process = new OtaResponse<>();
        try {
            process = csArticleModule.selectCsArticle(request);
        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(),null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return process;
    }
}

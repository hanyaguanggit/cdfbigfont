package cn.com.ktm.mt.model.article.request;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleReqVoBody implements Body {
    private Integer categoryId;

    @Override
    public void valid() {
        Assert.notNull(categoryId, ResponseConsts.PARAM_ERROR,"文章分类id为空。");
    }
}

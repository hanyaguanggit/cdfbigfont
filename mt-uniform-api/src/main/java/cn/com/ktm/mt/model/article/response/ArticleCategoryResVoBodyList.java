package cn.com.ktm.mt.model.article.response;

import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCategoryResVoBodyList implements Body {
    private List<ArticleCategoryResVoBody> articleCategoryResVoBodyList;
    @Override
    public void valid() {
    }
}

package cn.com.ktm.mt.model.article.response;

import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResVoBodyList implements Body {
    private List<ArticleResVoBody> articleList;

    @Override
    public void valid() {

    }
}

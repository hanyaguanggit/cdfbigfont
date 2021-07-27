package cn.com.ktm.mt.model.article.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResVoBodyList {
    private List<ArticleResVoBody> articleList;
}

package cn.com.ktm.mt.model.article.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResVoBody{
    private Integer articleId;//文章id
    private Integer categoryId;//文章栏目
    private String articleName;//中文标题
    private String articleNameEN;//英文标题
    private String authorName;//作者名称
    private Integer picId;//
    private String publishTime;//发布时间
    private Integer status;//文章状态
    private Integer sequence;//
    private String info;//文章简介
    private String content;//文章内容
    private Integer visitCount;//访问次数
}

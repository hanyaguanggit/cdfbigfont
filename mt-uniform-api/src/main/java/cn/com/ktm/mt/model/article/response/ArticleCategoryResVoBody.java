package cn.com.ktm.mt.model.article.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章分类返回体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCategoryResVoBody {
    private Integer id;//文章栏目id
    private String name;//
    private String nameEN;//
    private Integer parentId;//父节点id
    private Integer sequence;//排序
    private Integer status;//状态
    private String createTime;//创建时间
    private Integer creator;//创建人
    private String lastModifiedTime;//
    private Integer lastModifiedUser;//最后修改人
    private Integer shopId;//店铺id
}

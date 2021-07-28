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
public class SaveArticleCategoryReqVoBody implements Body {
    private Integer parentId;//父节点
    private String name;//中文名称
    private String nameEN;//英文名称
    private Integer status;//1：启用  2：停用
    private Integer sequence;//排序
    private Integer creator;//创建者
    private Integer shopId;//店铺id
    @Override
    public void valid() {
        Assert.notNull(parentId, ResponseConsts.PARAM_ERROR,"上级栏目id为空。");
        Assert.notNull(name,ResponseConsts.PARAM_ERROR,"中文名称为空。");
        Assert.notNull(status,ResponseConsts.PARAM_ERROR,"状态为空。");
        Assert.notNull(sequence,ResponseConsts.PARAM_ERROR,"排序为空。");
    }
}

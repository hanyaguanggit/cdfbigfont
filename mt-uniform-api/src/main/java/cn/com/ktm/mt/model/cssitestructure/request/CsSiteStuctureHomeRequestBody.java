package cn.com.ktm.mt.model.cssitestructure.request;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * hyg 查询离岛免税首页初始入参
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CsSiteStuctureHomeRequestBody implements Body {
    private List<Integer> siteStructureIdList;//网站结构父id集合  这里设计为list，网站结构id可以传多个，也可以传1个。
    private Integer templateId;//店铺id


    @Override
    public void valid() {
        Assert.notEmpty(siteStructureIdList, ResponseConsts.PARAM_ERROR);
        Assert.notNull(templateId, ResponseConsts.PARAM_ERROR);
    }
}

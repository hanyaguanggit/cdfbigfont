package cn.com.ktm.mt.model.sitestructure.request;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.Data;

@Data
public class CsSiteStuctureListRequest implements Body {
    private String id;

    @Override
    public void valid() {
        Assert.fail(ResponseConsts.PARAM_ERROR,"id为空。");
    }
}

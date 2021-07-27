package cn.com.ktm.mt.model.security.request.user;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnabledReqVoBody implements Body {
    private Integer enabled;//0未启用，1启动
    private Integer userId;
    @Override
    public void valid() {
    Assert.notNull(enabled,ResponseConsts.PARAM_ERROR,"是否启用状态未设置。");
    Assert.notNull(userId, ResponseConsts.PARAM_ERROR,"用户id为空。");
    }
}

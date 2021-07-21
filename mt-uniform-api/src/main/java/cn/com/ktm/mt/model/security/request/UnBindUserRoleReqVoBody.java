package cn.com.ktm.mt.model.security.request;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import cn.com.ktm.mt.model.message.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnBindUserRoleReqVoBody implements Body {
    private int userId;
    private int roleId;
    @Override
    public void valid() {
        Assert.notNull(userId, ResponseConsts.PARAM_ERROR,"用户id为空。");
        Assert.notNull(roleId,ResponseConsts.PARAM_ERROR,"角色id为空。");
    }
}

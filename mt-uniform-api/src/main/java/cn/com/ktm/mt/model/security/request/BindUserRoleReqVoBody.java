package cn.com.ktm.mt.model.security.request;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BindUserRoleReqVoBody implements Body {
    private int roleId;//角色id
    private List<Integer> userIds;//用户id
    @Override
    public void valid() {
        Assert.notNull(roleId, ResponseConsts.PARAM_ERROR,"角色id为空。");
        Assert.notEmpty(userIds,ResponseConsts.PARAM_ERROR,"用户id为空。");
    }
}

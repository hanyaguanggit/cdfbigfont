package cn.com.ktm.mt.model.security.request;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityRoleReqVoBody extends BaseRequestVo implements Body {
    private String roleName;//角色名称
    @Override
    public void valid() {
        Assert.notBlank(roleName,ResponseConsts.PARAM_ERROR,"角色名称为空。");
    }
}

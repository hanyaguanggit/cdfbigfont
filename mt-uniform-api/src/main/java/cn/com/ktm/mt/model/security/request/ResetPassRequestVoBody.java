package cn.com.ktm.mt.model.security.request;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Deprecated
public class ResetPassRequestVoBody implements Valid {
    private int userId;
    private String password;
    @Override
    public void valid() {
        Assert.notNull(userId, ResponseConsts.PARAM_ERROR,"用户id为空。");
        Assert.notNull(password,ResponseConsts.PARAM_ERROR,"密码为空。");
    }
}

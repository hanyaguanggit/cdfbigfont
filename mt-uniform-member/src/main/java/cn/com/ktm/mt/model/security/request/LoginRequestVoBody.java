package cn.com.ktm.mt.model.security.request;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * hyg  2021.07.07
 * 后台登录请求体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestVoBody implements Valid {
    private String userName;
    private String password;
    private String validCode;

    @Override
    public void valid() {
        Assert.notNull(userName, ResponseConsts.PARAM_ERROR,"用户名不能为空!");
        Assert.notNull(password,ResponseConsts.PARAM_ERROR,"登录密码为空!");
        Assert.notNull(validCode,ResponseConsts.PARAM_ERROR,"验证码为空！");
    }
}

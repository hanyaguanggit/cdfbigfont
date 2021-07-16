package cn.com.ktm.mt.model.security.request;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * hyg  2021.07.07
 * 登录请求体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestVo implements Valid {
    private String userName;
    private String password;

    @Override
    public void valid() {
        Assert.isNull(userName, ResponseConsts.TICKET_PARAM_ERROR,"用户名不能为空!");
        Assert.isNull(password,ResponseConsts.PARAM_ERROR,"登录密码为空!");
    }
}

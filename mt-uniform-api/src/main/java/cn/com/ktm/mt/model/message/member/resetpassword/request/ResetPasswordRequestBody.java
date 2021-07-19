package cn.com.ktm.mt.model.message.member.resetpassword.request;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResetPasswordRequestBody implements Body {
    private String oldPassword;//旧密码
    private String newPassword;//新密码
    private int userId;//用户id
    private int createUserId;//修改人id

    @Override
    public void valid() {
        Assert.notBlank(oldPassword, ResponseConsts.PARAM_ERROR,"当前密码为空");
        Assert.notBlank(newPassword, ResponseConsts.PARAM_ERROR,"新密码为空");
        Assert.notNull(userId,ResponseConsts.PARAM_ERROR,"用户id为空。");
        Assert.notNull(createUserId,ResponseConsts.PARAM_ERROR,"修改人id为空。");
    }
}

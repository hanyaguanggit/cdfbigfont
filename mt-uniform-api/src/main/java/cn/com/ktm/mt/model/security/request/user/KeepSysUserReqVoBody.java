package cn.com.ktm.mt.model.security.request.user;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * hyg
 * 保存系统用户入参
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeepSysUserReqVoBody implements Body {
    private Integer operateType;//操作类型
    private Integer userId;//用户id
    private String userName;
    private String loginName;
    private String password;
    private Integer avatar;//头像
    private String mobilePhoneNo;//手机
    private String email;//邮箱
    private Integer enabled;//0：启用 1：启用
    private String expiryDate;//失效日期
    private String roleIds;//角色id
    private Integer shopId;//1:离岛免税 2：会员购
        private Integer creator;//创建者
    @Override
    public void valid() {
        Assert.notNull(userName,ResponseConsts.PARAM_ERROR, "用户名称为空。");
        Assert.notNull(loginName,ResponseConsts.PARAM_ERROR,"登录名称为空。");
        Assert.notNull(password,ResponseConsts.PARAM_ERROR,"密码为空。");
        Assert.notNull(mobilePhoneNo,ResponseConsts.PARAM_ERROR,"手机号为空。");
        Assert.notNull(email,ResponseConsts.PARAM_ERROR,"邮箱为空。");
    }
}

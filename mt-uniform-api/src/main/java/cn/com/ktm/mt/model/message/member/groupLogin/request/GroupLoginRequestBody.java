package cn.com.ktm.mt.model.message.member.groupLogin.request;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupLoginRequestBody implements Body {

    private Integer userType;// ⽤户类型 1=散客 2=旅⾏社 3=特殊团队
    private String orgCode;// 组织机构代码（userType=2、3时必填）
    private String password ;// 密码
    private String validCode ;// 图形验证码
    private String validToken ;// 图形验证码标识

    @Override
    public void valid() {
        Assert.notNull(userType, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"⽤户类型为空");
        Assert.notBlank(orgCode,ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"组织机构代码为空");
        Assert.notBlank(password,ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"密码为空");
        Assert.notBlank(validCode,ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"图形验证码为空");
        Assert.notBlank(validToken,ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"图形验证码标识为空");

    }
}

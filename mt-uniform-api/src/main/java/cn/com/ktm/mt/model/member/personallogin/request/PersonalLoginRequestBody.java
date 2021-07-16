package cn.com.ktm.mt.model.member.personallogin.request;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalLoginRequestBody implements Body {

   // private Integer userType;//⽤户类型 官微暂时只⽀持散客 1=散客 2=旅⾏社 3=特殊团队
    private String phone;//⽤户电话
    private String smsCode;//短信验证码
    private String validCode;//图形验证码
    private String validToken;//图形验证码标识



    @Override
    public void valid() {
        Assert.notBlank(phone, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"⽤户电话为空");
        Assert.notBlank(smsCode, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"短信验证码为空");
        Assert.notBlank(validCode, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"图形验证码为空");
        Assert.notBlank(validToken, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"图形验证码标识为空");
    }

/*
    public UserEntity transform(PersonalLoginRequest request){
        UserEntity entity = new UserEntity();
        entity.setUname(request.getBody().getPhone());
        entity.setUserType(request.getBody().getUserType());
        entity.setPhone(request.getBody().getPhone());
        return entity;
    }*/



}

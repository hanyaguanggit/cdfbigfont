package cn.com.ktm.mt.model.member.sendsmscode.request;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SendSmsVeriﬁcationCodeRequestBody implements Body {
    //private Integer userType;//⽤户类型
    private String phone;// 电话号码  userType =1 必 填

    private String source; //请求来源
    @Override
    public void valid() {
       // Assert.notNull(userType, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"⽤户类型为空");
       // Assert.notNull(userType, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"请求来源为空");

        Assert.notBlank(phone, ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"电话号码为空");


    }
}

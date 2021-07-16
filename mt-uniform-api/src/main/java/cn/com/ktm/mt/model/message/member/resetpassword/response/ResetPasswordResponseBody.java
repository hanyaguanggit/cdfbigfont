package cn.com.ktm.mt.model.message.member.resetpassword.response;

import cn.com.ktm.mt.model.message.Body;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResetPasswordResponseBody implements Body {
    private Integer userType;
    private String orgCode;
    @Override
    public void valid() {

    }
}

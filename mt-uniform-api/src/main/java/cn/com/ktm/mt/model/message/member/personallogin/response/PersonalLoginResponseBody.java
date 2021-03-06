package cn.com.ktm.mt.model.message.member.personallogin.response;

import cn.com.ktm.mt.model.message.Body;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonalLoginResponseBody implements Body {

    private String userId;//系统商⽤户ID
    private String phone;//⽤户联系电话
    private Integer userType;//⽤户类型

    @Override
    public void valid() {

    }
}

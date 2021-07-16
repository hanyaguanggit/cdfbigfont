package cn.com.ktm.mt.model.message.member.groupverifynotice.response;

import cn.com.ktm.mt.model.message.Body;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupVerifyNotifyResponseBody implements Body {
    private Integer userType;//⽤户类型 1=散客 2=旅⾏社 3=特殊团队

    private String userId;//系统商⽤户ID

    @Override
    public void valid() {

    }
}

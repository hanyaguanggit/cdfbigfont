package cn.com.ktm.mt.model.message.member.groupLogin.response;

import cn.com.ktm.mt.model.message.Body;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupLoginResponseBody implements Body {
    private Integer userType;//⽤户类型 1=散客 2=旅⾏社 3=特殊团队
    private String orgCode;//组织机构代码，作为⽤户名
    private String userId ;//系统商⽤户ID

    @Override
    public void valid() {

    }
}

package cn.com.ktm.mt.model.message.member.validcode.request;

import cn.com.ktm.mt.model.message.Body;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidCodeRequestBody implements Body {

    private String validToken;// 图⽚验证码key（由"(类型)_(时间戳)_(标 识)”组合形成）

    @Override
    public void valid() {
    }
}

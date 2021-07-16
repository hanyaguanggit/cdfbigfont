package cn.com.ktm.mt.model.security.request;

import cn.com.ktm.mt.model.message.OtaResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestVo extends OtaResponse<LoginRequestVoBody> {

}

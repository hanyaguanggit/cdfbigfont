package cn.com.ktm.mt.model.security.request;

import cn.com.ktm.mt.model.message.OtaRequest;
import cn.com.ktm.mt.model.message.OtaResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
public class LoginRequestVo extends OtaRequest<LoginRequestVoBody> {

}

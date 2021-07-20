package cn.com.ktm.mt.model.security.response;
import cn.com.ktm.mt.model.bean.CsSecurityUser;
import cn.com.ktm.mt.model.message.OtaResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse extends OtaResponse<LoginResponseBody> {
      private List<LoginResponseBody> menuList;
      private CsSecurityUser csSecurityUser;
}

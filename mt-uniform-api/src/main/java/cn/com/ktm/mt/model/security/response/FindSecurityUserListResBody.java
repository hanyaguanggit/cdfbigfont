package cn.com.ktm.mt.model.security.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mockito.internal.matchers.Find;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindSecurityUserListResBody {
    private List<FindSecurityUserListResModel> userListResModelList;
}

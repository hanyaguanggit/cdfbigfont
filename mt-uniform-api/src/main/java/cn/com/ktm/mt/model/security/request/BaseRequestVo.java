package cn.com.ktm.mt.model.security.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequestVo {
    private int creator;//创建者id
    private String createTime;//创建时间
    private int lastModifiedUser;//最后修改者
    private String lastModifiedTime;//最后修改时间
}

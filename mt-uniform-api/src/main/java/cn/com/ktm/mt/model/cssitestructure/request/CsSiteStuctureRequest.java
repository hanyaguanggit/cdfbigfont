package cn.com.ktm.mt.model.cssitestructure.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsSiteStuctureRequest {

    private String id;
    private Integer parentId; //上级模块id
}

package cn.com.ktm.mt.model.sitestructure.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsSiteStuctureHomeResponseVo {
    private Integer sectionId;
    private Integer siteStructureId;
    private String sitestructureName;//网站结构名称
    private Integer type;//网站结构id的级别
    private String linkTab;
    private Integer shopId;//店铺id 1：离岛免税 2：会员购
    private Integer positionTypeId;//位置类型id
}

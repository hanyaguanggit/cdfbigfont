package cn.com.ktm.mt.model.home.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 首页返回体基础参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseHomeRes {
    private Integer id ;
    private Integer sectionId;
    private Integer siteStructureId;//网站结构id
    private Integer positionTypeId;//位置类型id
}

package cn.com.ktm.mt.model.section.request;

import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Deprecated
public class BaseSectionReq implements Body {
    private int siteStructureId; //网站结构id
    private int positionTypeId;  //位置类型id
    private String sectionName;  //模块名称
    private String fromTime;     //生效时间
    private String thruTime;     //到期时间
    private int status;          //模块状态
    private int target;          //是否首页展示
    private int sequence;        //排序
    @Override
    public void valid() {

    }
}

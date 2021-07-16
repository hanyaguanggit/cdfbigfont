package cn.com.ktm.mt.model.entity;

import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupAduitEntity implements Body {
    private int userType;
    private String phone;
    private String userId;
    private String orgCode;
    private int auditStatus ;
    private Long auditStatusChangedTime ;
    private int status;

    @Override
    public void valid() {
// TODO Auto-generated method stub
    }


}

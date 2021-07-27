package cn.com.ktm.mt.model.security.request.user;

import cn.com.ktm.mt.model.message.Body;
import cn.com.ktm.mt.model.security.request.BindUserRoleReqVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LockedReqVoBody implements Body {
    private Integer locked;//是否锁定 0：未锁定 1：锁定
    private Integer userId;
    
    @Override
    public void valid() {

    }
}

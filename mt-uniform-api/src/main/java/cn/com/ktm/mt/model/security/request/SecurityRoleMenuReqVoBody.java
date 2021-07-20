package cn.com.ktm.mt.model.security.request;

import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * hyg
 * 2021.07.20
 * 保存角色权限入参
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityRoleMenuReqVoBody implements Body {
    private int roleId;//角色id
    private List<Integer> menuIdList;//菜单id
    @Override
    public void valid() {

    }
}

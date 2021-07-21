package cn.com.ktm.mt.model.security.request;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import cn.com.ktm.mt.model.message.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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
    Assert.notNull(roleId, ResponseConsts.PARAM_ERROR,"角色id为空。");

    }

}

package cn.com.ktm.mt.model.security.response;

import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * hyg
 * 登录成功，查询菜单权限响应体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseBody implements Body {
    private Integer id;//菜单id
    private Integer parentId;//菜单父级id
    private String menuName;//菜单名称
    private Integer menuType;//菜单类型
    private Integer roleId;//角色id
    private String roleName;
    private Integer userId;
    private String loginName;

    @Override
    public void valid() {

    }
}

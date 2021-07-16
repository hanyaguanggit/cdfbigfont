package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUser implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;

    private Long id;
    private String loginName;
    private String password;
    private String name;//用户姓名。
    private String phone;
    private String code;
    private Integer type;//0=普通职员，1=组长，2=正式售票员，3=临时售票员，4=验票员，99=超级管理员。
    private String remark;
    private Long createTime;
    private Long updateTime;
    private Integer isDelete;//1未删（默认）,0 已删
    private Integer status;//1可用（默认），0不可用
    private Long createUser;
    private Long updateUser;
    private Long roleId;//角色ID
    private String roleName;//角色名称
    private Long depId;

    //private Integer[] roles;

    //private List<AdminMenu> adminMenus;
    //private String createUserName;
}

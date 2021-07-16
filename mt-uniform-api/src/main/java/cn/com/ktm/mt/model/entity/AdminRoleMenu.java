package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRoleMenu implements Serializable {
	private static final long serialVersionUID = -5809782578272943999L;
	private Long id;
	private Long roleId;
	private Long menuId;
	private Integer isDelete;//1未删（默认）,0 已删
	private Integer type;//1:菜单；2：票
	private Long createUser;
	private Long createTime;
	private Long updateUser;
	private Long updateTime;
}

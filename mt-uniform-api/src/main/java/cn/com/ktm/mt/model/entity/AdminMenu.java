package cn.com.ktm.mt.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminMenu implements Serializable {
	private static final long serialVersionUID = -5809782578272943999L;

	private Long id;
	private String name;
	private String icon;
	private Long parentId;
	private Integer sort;
	private Integer enable;
	private Integer type;//1后台，2现场
	private String urlPath;
	private Integer isDelete;//1未删（默认）,0 已删
	private Integer status;//1可用（默认），0不可用
	private Integer buttonType;//按钮类型
	private Integer menuType;//菜单类型
	private boolean checked;//是否被选中

	public AdminMenu(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public AdminMenu() {
		super();
	}

	public AdminMenu(Long id, String name, Long parentId, Integer sort, Integer enable,
                     Integer type, String urlPath, Integer isDelete, Integer status) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.sort = sort;
		this.enable = enable;
		this.type = type;
		this.urlPath = urlPath;
		this.isDelete = isDelete;
		this.status = status;
	}

	public AdminMenu(Long id, String name, Long parentId, Integer sort, Integer enable,
                     Integer type, String urlPath, Integer isDelete, Integer status,
                     boolean checked) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.sort = sort;
		this.enable = enable;
		this.type = type;
		this.urlPath = urlPath;
		this.isDelete = isDelete;
		this.status = status;
		this.checked = checked;
	}
}

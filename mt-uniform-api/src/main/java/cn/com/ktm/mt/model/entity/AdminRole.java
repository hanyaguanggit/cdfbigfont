package cn.com.ktm.mt.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminRole implements Serializable {
	private static final long serialVersionUID = -5809782578272943999L;

	private Long id;
	private String name;
	private Integer type;//1后台，2现场
	private Integer isDelete;//1未删（默认）,0 已删
	private Integer status;//1可用（默认），0不可用


	public AdminRole(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public AdminRole() {
		super();
	}

	public AdminRole(Long id, String name,
                Integer type,  Integer isDelete, Integer status) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.isDelete = isDelete;
		this.status = status;
	}
}

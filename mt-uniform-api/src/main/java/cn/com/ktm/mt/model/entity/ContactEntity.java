package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 联系人
 *
 * @author dongdongzhang
 * @create 2017-05-09 14:06
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactEntity {
	private Long userId;
	private Integer certificateType;
	private String cardNo;
	private String name;
	private String phone;
	private String remark;
	private Long id;
	private Long createUser;
	private Long createTime;
	private Long updateUser;
	private Long updateTime;
	private String ip;
	private Integer status;//1可用（默认）,0不可用
	private Integer isDelete;//1已删除, 0未删除（默认）

}

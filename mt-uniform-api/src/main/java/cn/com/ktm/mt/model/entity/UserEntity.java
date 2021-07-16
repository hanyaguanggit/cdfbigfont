package cn.com.ktm.mt.model.entity;


import cn.com.ktm.mt.model.util.utils.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
	private static final long serialVersionUID = -5809782578272943999L;

	private String  uname;
	private String  password;
	private Integer userType;
	private Long    organizationId;
	private Integer certificateType;
	private String  certificateNo;
	private String  phone;
	private Integer onlineStatus;
	private Long    id;
	private Long    createUser;
	private Long    createTime;
	private Long    updateUser;
	private Long    updateTime;
	private String  ip;
	private String  agent;
	private Integer status;
	private Integer isDelete;
	private String  email;
	private Integer isBind;
	private Long    bindTime;
	private String  openId;
	private String alipayUserId;

	public UserEntity(String uname, String password, Integer userType, String phone, Integer status, String  email) {
		super();
		this.uname = uname;
		this.password = password;
		this.userType = userType;
		this.phone = phone;
		this.createTime = DateTimeUtil.getCurTime();
		this.status = status;
		this.email=email;
	}


	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class RefundEntity implements Serializable {
		private static final long serialVersionUID = -5809782578272943999L;

		private Long orderId;
		private String orderDetails;
		private String refundTicketInfo;
		private Double total;
		private Long applyUserId;
		private Integer auditTime;
		private Integer auditUserType;
		private Integer auditUserId;
		private Integer auditStaus;
		private String devRemark;
		private Long id;
		private Long createUser;
		private Integer createTime;
		private Long updateUser;
		private Integer updateTime;
		private String ip;
		private Integer status;
		private Integer isDelete;
		private String remark;
	}
}

package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationEntity implements Serializable {
	private static final long serialVersionUID = -5809782578272943999L;

	private String regName;
	private String contactName;
	private String contactFile;
	private String phone;
	private String cardNo;
	private String orgSub;
	private String orgCode;
	private String orgCodeFile;
	private String regCode;
	private String regCodeFile;
	private String licenseFile;
	private String introFile;
	private Long verifiedTime;
	private Long verifiedUser;
	private String email;
	private String orgAddress;
	private Integer type;
	private Integer refundScale;
	private Integer auditStatus;
	private Integer isSign;
	private Integer isSupportNotRealName;
	private Integer signScale;
	private Long signValidDate;
	private Long id;
	private Long createUser;
	private Long createTime;
	private Long updateUser;
	private Long updateTime;
	private String ip;
	private Integer status;
	private Integer isDelete;
	private String remark;
	private Integer renewStatus;
	private Integer freeFrozenTag;
	private Integer frozenBeginDate;
	private Integer frozenEndDate;

	private String userName;
	private MultipartFile file;

	private String signValidDateStr;

	private Integer score;

	//add 2020.2.15 By yuanpeng
	private String policyFiles;
	private String subOrgFiles;
	private String password;


}

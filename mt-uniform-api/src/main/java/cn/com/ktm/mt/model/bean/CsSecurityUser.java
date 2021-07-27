package cn.com.ktm.mt.model.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户 cs_security_user
 */
@Data
public class CsSecurityUser  implements Serializable{
    private Integer id;

    private String userName;

    private String password;

    private Date expiryDate;

    private Boolean locked;

    private Date credentialExpiryDate;

    private Boolean enabled;

    private Integer avatar;

    private String loginName;

    private String mobilePhoneNo;

    private String email;

    private Date createDate;

    private Short deleted;

    private Integer creator;

    private Integer lastModifiedUser;

    private Date lastModifiedTime;

    private Integer shopId;


}
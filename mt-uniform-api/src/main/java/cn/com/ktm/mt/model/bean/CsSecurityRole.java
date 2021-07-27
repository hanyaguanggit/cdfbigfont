package cn.com.ktm.mt.model.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class CsSecurityRole implements Serializable {
    private Integer id;

    private String name;

    private String code;

    private Date createTime;

    private Integer creator;

    private Date lastModifiedTime;

    private Integer lastModifiedUser;


}
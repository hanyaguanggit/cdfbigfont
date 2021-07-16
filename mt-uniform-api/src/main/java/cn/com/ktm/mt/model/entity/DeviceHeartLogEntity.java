package cn.com.ktm.mt.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeviceHeartLogEntity implements Serializable {
    private String deviceCode;
    private Long uploadTime;
    private String userName;
    private Integer opType;
    private Integer currentDate;
    private Long id;
    private Long createUser;
    private Long createTime;
    private Long updateUser;
    private Long updateTime;
    private String ip;
    private Integer status;
    private Integer isDelete;
    private String remark;
}

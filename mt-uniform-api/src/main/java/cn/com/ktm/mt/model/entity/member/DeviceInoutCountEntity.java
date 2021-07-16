package cn.com.ktm.mt.model.entity.member;

import lombok.Data;

@Data
public class DeviceInoutCountEntity {
    private Long id;

    private Long inOutTime;

    private Integer insideInCount;

    private Integer insideOutCount;

    private Long deviceId;

    private Long createUser;

    private Long createTime;

    private Long updateUser;

    private Long updateTime;

    private String ip;

    private Integer status;

    private Integer isDelete;

    private String remark;

    private Integer createMethod;

    private Integer notifyStatus;

}
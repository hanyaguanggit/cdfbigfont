package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZCC
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsTempletEntity {

    private Long    id;
    private Integer orderChannel;
    private Integer userType;
    private Integer teamType;
    private Integer actionType;
    private String smsKey;
    private String  smsValue;
    private Long    createUser;
    private Long    createTime;
    private Long    updateUser;
    private Long    updateTime;
    private String  ip;
    private Integer status;
    private Integer isDelete;
    private String  remark;

}

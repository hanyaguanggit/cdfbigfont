package cn.com.ktm.mt.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信模板
 * @author zhangdongdong
 * @date created in 15:28. 2018/1/16.
 * @description:
 * @modified by:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsTempletBean {

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
    private Integer status;//1可用（默认）,0不可用
    private Integer isDelete;//1已删除, 0未删除（默认）
    private String  remark;

}

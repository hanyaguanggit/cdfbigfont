package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelEntity {
    private Long id;
    private String channelName;
    private String userName;

    private String contactName;
    private String contactPhone;
    private Integer isSupportOrder;
    private Integer type;
    private String password;
    private BigDecimal minBalance;
    private BigDecimal accountBalance;
    private String privateKey;
    private String publicKey;
    private Long createUser;
    private Long createTime;
    private Long updateUser;
    private Long updateTime;
    private String ip;
    private int status;
    private int isDelete;
    private String remark;
    private String channelNo;
    private Long version;
    private String ticketNotifyUrl;
    private String checkNotifyUrl;
    private String refundNotifyUrl;

}
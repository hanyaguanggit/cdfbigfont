package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerEntity {
    private String id;
    private String clientId;
    private String partnerName;
    private String contactPhone;
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
    private Long version;
    private String ticketNotifyUrl;
    private String checkNotifyUrl;
    private String refundNotifyUrl;
    private String payNotifyUrl;

    private String orgAuditNotifyUrl;
    private String channelStatusNotifyUrl;
    private String productChangeNotifyUrl;

    private String reservationNotifyUrl;
    private String leaveParkNotifyUrl;
    private String levelMappingNoticeUrl;
    private String priceBaseNoticeUrl;
}
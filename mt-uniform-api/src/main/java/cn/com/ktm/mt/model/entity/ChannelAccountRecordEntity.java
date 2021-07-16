package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelAccountRecordEntity {
    private Long id;

    private Long orderId;

    private String channelOrderId;

    private BigDecimal tradeMoney;

    private Integer tradeType;

    private Long channelId;

    private Long createUser;

    private Long createTime;

    private Long updateUser;

    private Long updateTime;

    private Integer status;

    private Integer isDelete;

    private String remark;

    private BigDecimal accountBalance;
    private String ip;
    private String optUser = "系統";

    public ChannelAccountRecordEntity(Long orderId, String channelOrderId, BigDecimal tradeMoney, Integer tradeType, Long channelId, Long createUser, Long createTime, BigDecimal accountBalance) {
        this.orderId = orderId;
        this.channelOrderId = channelOrderId;
        this.tradeMoney = tradeMoney;
        this.tradeType = tradeType;
        this.channelId = channelId;
        this.createUser = createUser;
        this.createTime = createTime;
        this.accountBalance = accountBalance;
    }

    public ChannelAccountRecordEntity(Long orderId, String channelOrderId, BigDecimal tradeMoney, Integer tradeType, Long channelId, Long createUser, Long createTime) {
        this.orderId = orderId;
        this.channelOrderId = channelOrderId;
        this.tradeMoney = tradeMoney;
        this.tradeType = tradeType;
        this.channelId = channelId;
        this.createUser = createUser;
        this.createTime = createTime;
    }
}
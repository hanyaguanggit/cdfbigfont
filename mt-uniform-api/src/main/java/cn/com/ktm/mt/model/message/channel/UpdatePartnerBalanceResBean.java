package cn.com.ktm.mt.model.message.channel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 更新渠道库存的响应类
 * Created by 陈臻 on 2018/6/29.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePartnerBalanceResBean {
    private String partnerId;

    private String partnerName;

    private Long minBalance;

    private Long accountBalance;

    private Boolean isSuccess;
}

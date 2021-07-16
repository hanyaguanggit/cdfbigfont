package cn.com.ktm.mt.model.message.channel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerChannelReBean {
    private Integer channelId;
    private Integer status;
    private Integer partnerId;
}

package cn.com.ktm.mt.model.message.partnerchannel;

import cn.com.ktm.mt.model.entity.PartnerChannelEntity;
import cn.com.ktm.mt.model.message.OtaRequest;
import lombok.Data;

@Data
public class SyncMTChannelStatusRequest extends OtaRequest<PartnerChannelEntity> {
}

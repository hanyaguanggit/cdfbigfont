package cn.com.ktm.mt.model.message.partnerchannel;

import cn.com.ktm.mt.model.entity.PartnerChannelEntity;
import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllChannelsResponsbody implements Body {

    private List<PartnerChannelEntity> channelInfoList;
    @Override
    public void valid() {

    }
}

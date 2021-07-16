package cn.com.ktm.mt.model.message.closeorder;


import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CloseOrderRequestBody implements Body {
    private String channelOrderId; //String 是 渠道订单ID


    @Override
    public void valid() {
        Assert.notNull(channelOrderId, ResponseConsts.PARAM_ERROR);
    }
}

package cn.com.ktm.mt.model.message.channelrefund;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CRReuestBody implements Body {

    private String channelOrderId;
    private String orderId;
    private String refundSerialNo;
    private String[] voucherList;
    private Integer voucherType;
    private Integer refundPrice;
    private Integer refundMessageType;
    private String operator;
    private String reason;
    private String refundTime;
    private Integer count;

    @Override
    public void valid() {
        Assert.notBlank(channelOrderId, ResponseConsts.CHANNEL_QUERY_ERROR, "渠道订单ID为空");
        Assert.notNull(orderId, ResponseConsts.CHANNEL_QUERY_ERROR, "订单ID为空");
        Assert.notNull(refundPrice, ResponseConsts.CHANNEL_QUERY_ERROR, "refundPrice为空");
        Assert.notNull(refundMessageType, ResponseConsts.CHANNEL_QUERY_ERROR, "退款类型为空");
        Assert.notNull(reason, ResponseConsts.CHANNEL_QUERY_ERROR, "原因为空");
        Assert.notNull(refundTime, ResponseConsts.CHANNEL_QUERY_ERROR, "退款时间为空");
        Assert.notNull(count, ResponseConsts.CHANNEL_QUERY_ERROR, "数量为空");
    }
}

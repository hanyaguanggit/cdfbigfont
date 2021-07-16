package cn.com.ktm.mt.model.message.partnerchannel;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllChannelsRequest implements Body {
    private  Integer pageSize;
    private  Integer pageIndex;

    @Override
    public void valid() {
        Assert.notNull(this.pageIndex, ResponseConsts.SYNC_ORDER_PARAM_ERROR,"参数错误");
        Assert.notNull(this.pageSize, ResponseConsts.SYNC_ORDER_PARAM_ERROR,"参数错误");
    }
}

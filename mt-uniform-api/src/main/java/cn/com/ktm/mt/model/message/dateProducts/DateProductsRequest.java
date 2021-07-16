package cn.com.ktm.mt.model.message.dateProducts;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.OtaRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateProductsRequest extends OtaRequest<DateProductsRequestBody> {

    private String  partnerId;
    private Long  channelId;

    @Override
    public void valid() {
        Assert.notBlank(this.getPartnerId(), ResponseConsts.TICKET_OTHER_ERROR,"商家Id不能为空");
        Assert.notNull(this.getChannelId(), ResponseConsts.TICKET_OTHER_ERROR,"渠道Id不能为空");
        Assert.notNull(this.getBody().getPriceDate(), ResponseConsts.TICKET_OTHER_ERROR,"日期不能为空");

        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(this.getBody().getPriceDate());
        } catch (ParseException e) {
            // e.printStackTrace();
            Assert.isTrue(false,0,"日期格式有误");
        }

    }

}
package cn.com.ktm.mt.model.message.dateProducts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateProductItemEntity {

    private String partnerId;          //商家 id
    private Long productId;          //产品 Id
    private String channelId;               //渠道 id  "channelId": [2]
    private String productName;      //产品名称
    private Long stadiumId;         //场馆 ID（景区的子 POI）
    private String stadiumName;     //场馆名称
    private String priceDate;       //价格日期 格式 yyyy-MM-dd
    private Long levelId;         //如果请求参数有 levelId，需要回传，美团侧会做校验
    private Integer  marketPrice;    //市场价(单位分)
    private Integer channelPrice;    //渠道价(单位分)，用户在渠道侧下单的单价
    private Integer settlementPrice; //结算价(单位分)
    private Integer stock;           //日库存(指剩余可售卖库存)

}

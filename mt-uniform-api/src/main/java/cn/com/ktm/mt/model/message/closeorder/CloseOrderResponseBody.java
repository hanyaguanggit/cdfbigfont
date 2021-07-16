package cn.com.ktm.mt.model.message.closeorder;

import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CloseOrderResponseBody implements Body {

   	Integer  orderId;//美团订单ID，必须回传
    String partnerOrderId ;//合作方订单ID，必须回传


    @Override
    public void valid() {

    }
}

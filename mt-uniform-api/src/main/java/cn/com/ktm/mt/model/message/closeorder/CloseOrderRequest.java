package cn.com.ktm.mt.model.message.closeorder;

import cn.com.ktm.mt.model.message.OtaRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CloseOrderRequest extends OtaRequest<CloseOrderRequestBody> {
}

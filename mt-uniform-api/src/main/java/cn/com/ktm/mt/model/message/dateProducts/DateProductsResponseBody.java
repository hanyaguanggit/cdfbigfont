package cn.com.ktm.mt.model.message.dateProducts;

import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateProductsResponseBody implements Body {

    private List<DateProductItemEntity> DateProductItem;

    @Override
    public void valid() {}
}
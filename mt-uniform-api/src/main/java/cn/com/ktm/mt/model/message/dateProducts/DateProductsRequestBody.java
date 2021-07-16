package cn.com.ktm.mt.model.message.dateProducts;

import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateProductsRequestBody implements Body {

    private String  priceDate;  //价格日期 格式 yyyy-MM-dd  2020-09-01
    private boolean online;     //是否在线，false:不在线，true:在线  true
    private Integer levelId;    //回传上述【分时基础结构接口】返回的 levelId,如果不传该参数,查询当天的所有产品

    @Override
    public void valid() {

    }

}
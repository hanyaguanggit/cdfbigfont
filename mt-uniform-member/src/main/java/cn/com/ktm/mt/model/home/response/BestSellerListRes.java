package cn.com.ktm.mt.model.home.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 畅销榜单商品返回实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestSellerListRes extends BaseHomeRes {
    private Integer picId; //自定义图片Id
    private List<Integer> goodsIds;//推荐货品id
    private Integer displayType;//显示类型 1=红色
}

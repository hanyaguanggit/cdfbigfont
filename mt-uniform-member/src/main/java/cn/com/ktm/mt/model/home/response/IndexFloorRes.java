package cn.com.ktm.mt.model.home.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * hyg
 * 首页商品楼层响应体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexFloorRes extends BaseHomeRes {
    private String title;//楼层标题
    private Integer picId;//图片id
    private Integer linkType;//链接类型 0=默认跳转链接 1=自定义链接
    private String linkUrl;//跳转地址
    private String goodsIds;//商品ids
    private List<Integer> goodsIdsList;//商品id列表
}

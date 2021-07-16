package cn.com.ktm.mt.model.home.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 推荐商品返回
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendProductRes extends BaseHomeRes {
    private List<Integer> productLinks;
}

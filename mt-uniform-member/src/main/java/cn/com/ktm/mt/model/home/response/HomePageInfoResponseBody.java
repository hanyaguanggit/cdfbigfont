package cn.com.ktm.mt.model.home.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageInfoResponseBody {
    private List<PictureLinkRes> pictureLinkResList; //图片信息
    private List<BestSellerListRes> bestSellerListResList;//销售榜单信息
    private List<RecommendProductRes> recommendProductResList;//推荐商品
    private List<IndexFloorRes> indexFloorResList;//首页楼层
    private List<RichTextRes> richTextResList;//富文本
    private List<SearchHotWordsRes> searchHotWordsResList;//搜索热词
    private List<TextLinkRes> textLinkResList;//文字链接
}

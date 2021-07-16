package cn.com.ktm.mt.model.section.request;
import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsSurfaceSectionRequest implements Body {

    private int templateId;      //模块模板id
    private int siteStructureId; //网站结构id
    private int positionTypeId;  //位置类型id
    private int sectionId;       //网站模块id
    private String sectionName;  //模块名称

    private String fromTime;     //生效时间
    private String thruTime;     //到期时间

    private int status;          //模块状态
    private int target;          //是否首页展示
    private int sequence;        //排序
    private int creator;         //创建者
    private int lastModifiedUser;//最后更新人
    private int shopId;          //店铺id

    //private List<Integer> productIdsList;//商品id集合
   // private List<Integer> brandIdsList;//品牌id集合

    //创建实体所需参数
    private int positionId;      //模块位置id
    private int templateConfigId;//模块配置id

    //轮播图
    private int picture; //图片编号
    private String url; //图片链接

    //文字链接
    private String textName;
    private String textUrl;

    //富文本
    private String richContent;

    //商品推荐
    private String productTitle;//主标题
    private String productDescription;//副标题
    private String productLink;//商品链接

    //品牌推荐
    private String brandIds;

    //推荐文章
    private int articleId;
    private String articleUrl;

    //热门搜索词
    private int recommend;//是否主推搜索词
    private String searchText;//搜索文字名称
    private int linkType;//链接类型
    private String linkUrl;//链接地址

    //畅销榜单
    private int pictureId;
    private int displayType;//显示方案
    private String goodsIds;//商品id

    @Override
    public void valid() {

    }
}

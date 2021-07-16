package cn.com.ktm.mt.model.home.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchHotWordsRes extends BaseHomeRes {
    //热门搜索词
    private int recommend;//是否主推搜索词
    private String searchText;//搜索文字名称
    private int linkType;//链接类型
    private String linkUrl;//链接地址
}

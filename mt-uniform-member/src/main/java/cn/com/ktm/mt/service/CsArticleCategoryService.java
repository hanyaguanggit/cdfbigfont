package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsArticleCategoryMapper;
import cn.com.ktm.mt.model.bean.CsArticleCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsArticleCategoryService {
    @Autowired
    private CsArticleCategoryMapper csArticleCategoryMapper;

public List<CsArticleCategory> selectArticleCategory(Integer shopId){
    return csArticleCategoryMapper.selectByShopId(shopId);
}

public boolean saveArtcleCategory(CsArticleCategory csArticleCategory){
    int save = csArticleCategoryMapper.insert(csArticleCategory);
    if(save > 0){
        return true;
    }
    return false;
}
}

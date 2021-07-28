package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsArticleMapper;
import cn.com.ktm.mt.model.bean.CsArticle;
import cn.com.ktm.mt.model.bean.CsArticleWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsArticleService {
    @Autowired
    private CsArticleMapper csArticleMapper;

    public List<CsArticleWithBLOBs> selectByCategoryId(Integer categoryId,Integer shopId){
       return  csArticleMapper.selectByCategoryId(categoryId,shopId);
    }
}

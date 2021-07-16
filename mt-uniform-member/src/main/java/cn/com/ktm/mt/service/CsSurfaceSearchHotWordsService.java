package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSurfaceSearchHotWordsMapper;
import cn.com.ktm.mt.model.CsSurfaceSearchHotWords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsSurfaceSearchHotWordsService {
    @Autowired
    private CsSurfaceSearchHotWordsMapper csSurfaceSearchHotWordsMapper;

    public int addSearchHotWordsModel(CsSurfaceSearchHotWords csSurfaceSearchHotWords){
        int add = csSurfaceSearchHotWordsMapper.insertSelective(csSurfaceSearchHotWords);
        return add > 0 ? add : 0 ;
    }
}

package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSurfaceRecommendProductMapper;
import cn.com.ktm.mt.model.CsSurfaceRecommendProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsSurfaceRecommendProductService {
    @Autowired
    private CsSurfaceRecommendProductMapper csSurfaceRecommendProductMapper;

    public int addRecommendProductModel(CsSurfaceRecommendProduct csSurfaceRecommendProduct){
       int add =  csSurfaceRecommendProductMapper.insertSelective(csSurfaceRecommendProduct);
       return add > 0 ? add:0;
    }
}

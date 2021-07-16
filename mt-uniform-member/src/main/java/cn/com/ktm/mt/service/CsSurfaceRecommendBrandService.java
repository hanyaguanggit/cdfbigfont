package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSurfaceRecommendBrandMapper;
import cn.com.ktm.mt.model.CsSurfaceRecommendBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsSurfaceRecommendBrandService {
    @Autowired
    private CsSurfaceRecommendBrandMapper csSurfaceRecommendBrandMapper;

    public int addRecommendBrand(CsSurfaceRecommendBrand csSurfaceRecommendBrand){
        int add = csSurfaceRecommendBrandMapper.insertSelective(csSurfaceRecommendBrand);
        return add > 0 ? add : 0;
    }
}

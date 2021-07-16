package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsProductMapper;
import cn.com.ktm.mt.model.CsProductWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsProductService {
    @Autowired
    private CsProductMapper csProductMapper;
    //根据id集合查询产品信息
    public List<CsProductWithBLOBs>  selectProductByIds(List<Integer> ids){
       return csProductMapper.selectProductByIds(ids);
    }

}

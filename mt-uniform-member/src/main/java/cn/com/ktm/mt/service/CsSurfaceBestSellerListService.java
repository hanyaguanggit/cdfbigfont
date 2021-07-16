package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSurfaceBestSellerListMapper;
import cn.com.ktm.mt.model.CsSurfaceBestSellerList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsSurfaceBestSellerListService {
    Logger logger = LoggerFactory.getLogger(CsSurfaceBestSellerListService.class);
    @Autowired
    private CsSurfaceBestSellerListMapper csSurfaceBestSellerListMapper;

    public int addBestSellerListModel(CsSurfaceBestSellerList csSurfaceBestSellerList){
        int  add = csSurfaceBestSellerListMapper.insertSelective(csSurfaceBestSellerList);
        return add > 0 ? add :0;
    }
}

package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsSurfaceBestSellerList;
import org.springframework.stereotype.Repository;

@Repository
public interface CsSurfaceBestSellerListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSurfaceBestSellerList record);

    int insertSelective(CsSurfaceBestSellerList record);

    CsSurfaceBestSellerList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSurfaceBestSellerList record);

    int updateByPrimaryKey(CsSurfaceBestSellerList record);
}
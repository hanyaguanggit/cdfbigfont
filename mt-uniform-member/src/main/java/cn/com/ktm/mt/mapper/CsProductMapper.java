package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsProduct;
import cn.com.ktm.mt.model.CsProductWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CsProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsProductWithBLOBs record);

    int insertSelective(CsProductWithBLOBs record);

    CsProductWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsProductWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CsProductWithBLOBs record);

    int updateByPrimaryKey(CsProduct record);

    List<CsProductWithBLOBs> selectProductByIds(@Param("ids") List<Integer> ids);
}
package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.bean.CsArticle;
import cn.com.ktm.mt.model.bean.CsArticleWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CsArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsArticleWithBLOBs record);

    int insertSelective(CsArticleWithBLOBs record);

    CsArticleWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CsArticleWithBLOBs record);

    int updateByPrimaryKey(CsArticle record);

    List<CsArticleWithBLOBs> selectByCategoryId(@Param("categoryId") Integer categoryId,@Param("shopId") Integer shopId);
}
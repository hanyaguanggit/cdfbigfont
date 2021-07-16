package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsSurfaceTextLink;
import org.springframework.stereotype.Repository;

@Repository
public interface CsSurfaceTextLinkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSurfaceTextLink record);

    int insertSelective(CsSurfaceTextLink record);

    CsSurfaceTextLink selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSurfaceTextLink record);

    int updateByPrimaryKey(CsSurfaceTextLink record);
}
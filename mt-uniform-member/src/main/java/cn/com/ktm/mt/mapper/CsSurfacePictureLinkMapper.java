package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsSurfacePictureLink;
import org.springframework.stereotype.Repository;

@Repository
public interface CsSurfacePictureLinkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSurfacePictureLink record);

    int insertSelective(CsSurfacePictureLink record);

    CsSurfacePictureLink selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSurfacePictureLink record);

    int updateByPrimaryKey(CsSurfacePictureLink record);
}
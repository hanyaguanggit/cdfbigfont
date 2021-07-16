package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsStructurePosition;
import org.springframework.stereotype.Repository;

@Repository
public interface CsStructurePositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsStructurePosition record);

    int insertSelective(CsStructurePosition record);

    CsStructurePosition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsStructurePosition record);

    int updateByPrimaryKey(CsStructurePosition record);


}
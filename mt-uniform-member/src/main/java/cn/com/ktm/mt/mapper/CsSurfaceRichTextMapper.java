package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsSurfaceRichText;
import cn.com.ktm.mt.model.CsSurfaceRichTextWithBLOBs;
import org.springframework.stereotype.Repository;

@Repository
public interface CsSurfaceRichTextMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSurfaceRichTextWithBLOBs record);

    int insertSelective(CsSurfaceRichTextWithBLOBs record);

    CsSurfaceRichTextWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSurfaceRichTextWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CsSurfaceRichTextWithBLOBs record);

    int updateByPrimaryKey(CsSurfaceRichText record);
}
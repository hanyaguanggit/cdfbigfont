package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.CsSiteStructure;
import cn.com.ktm.mt.model.sitestructure.response.CsSiteStuctureHomeResponseVo;
import cn.com.ktm.mt.model.sitestructure.response.HomePageResponseBody;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CsSiteStructureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsSiteStructure record);

    int insertSelective(CsSiteStructure record);

    CsSiteStructure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsSiteStructure record);

    int updateByPrimaryKey(CsSiteStructure record);

    //通过父节点查找子节点网站结构
    List<CsSiteStructure> findInfoByParentId(Integer parentId);

    ///根据首页的网站结构id和客服端类型id查询信息（默认：templateId = 6)
    List<CsSiteStuctureHomeResponseVo> showSiteStructureHome(@Param("list") Set<Integer> siteStructureIdList,
                                                             @Param("templateId") Integer templateId);

    HomePageResponseBody findHomeRecommendDto(
            @Param("sectionId") Integer sectionId,
            @Param("tableName") String tableName);

}
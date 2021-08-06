package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSiteStructureMapper;
import cn.com.ktm.mt.model.CsSiteStructure;
import cn.com.ktm.mt.model.cssitestructure.response.CsSiteStuctureHomeResponseVo;
import cn.com.ktm.mt.model.home.response.HomePageResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class CsSiteStructureService {
    @Autowired
    private CsSiteStructureMapper csSiteStructureMapper;

    public CsSiteStructure findCsSiteStructureById(Integer sid) {

        return csSiteStructureMapper.selectByPrimaryKey(sid);

    }

    /**
     * 根据上级id获取网站信息（例如首页：45001。。。）
     * @param siteStructureIdList
     * @return
     */
    public List<CsSiteStructure> findCsSiteStructureByParentId(Integer siteStructureIdList) {

        return csSiteStructureMapper.findInfoByParentId(siteStructureIdList);

    }


    /**
     * 首页展示，根据首页所在网站结构集合和店铺id查询
     * @param siteStructureIdList
     * @param templateId
     * @return
     */
    public List<CsSiteStuctureHomeResponseVo> showSiteStructureHome(Set<Integer> siteStructureIdList, Integer templateId){
        return csSiteStructureMapper.showSiteStructureHome(siteStructureIdList,templateId);
    }

   public HomePageResponseBody findHomeRecommendDto(Integer sectionId ,String tableName){
   return csSiteStructureMapper.findHomeRecommendDto(sectionId,tableName);
   }
}

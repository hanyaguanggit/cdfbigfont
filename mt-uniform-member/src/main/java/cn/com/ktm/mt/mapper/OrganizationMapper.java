package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.entity.OrganizationEntity;
import cn.com.ktm.mt.model.message.member.announcement.AnnouncementBean;
import cn.com.ktm.mt.model.message.member.announcement.info.AnnoInfoRespBody;
import cn.com.ktm.mt.model.util.SQLProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuanpeng
 * @data 2020/02/20
 */
@Repository
public interface OrganizationMapper {

    OrganizationEntity findGroupByOrgCodeAndUserType(@Param("orgCode") String orgCode,
                                                     @Param("type") Integer userType);

    int insertSelective(OrganizationEntity entity);

    int updateById(OrganizationEntity organization);

    OrganizationEntity findOrganizationById(Long id);

    OrganizationEntity queryGroupNotice(OrganizationEntity request);

    OrganizationEntity findGroupByOrgCodeAndUserTypeAndUserId(@Param("orgCode") String orgCode,
                                                              @Param("type") Integer userType,
                                                              @Param("id") String userId);

    OrganizationEntity findGroupByOrgCode(String orgCode);

    OrganizationEntity findGroupById(String userId);

    @SelectProvider(type = SQLProvider.class, method = "getPartnerId")
    String getPartnerId(Integer id);

    @SelectProvider(type = SQLProvider.class, method = "getAnnouncementList")
    List<AnnouncementBean> getAnnouncementList(Integer pageSize , Integer PageIndex);

    @SelectProvider(type = SQLProvider.class, method = "getAnnouncementInfo")
    AnnoInfoRespBody getAnnouncementInfo(String noticeId);


}

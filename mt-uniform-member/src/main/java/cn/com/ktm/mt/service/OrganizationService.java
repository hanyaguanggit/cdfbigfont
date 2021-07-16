package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.OrganizationMapper;
import cn.com.ktm.mt.model.entity.OrganizationEntity;
import cn.com.ktm.mt.model.enums.IsDeleteEnum;
import cn.com.ktm.mt.model.enums.StatusEnum;
import cn.com.ktm.mt.model.util.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    public OrganizationEntity groupRegister(OrganizationEntity entity) {
        entity.setCreateTime(DateTimeUtil.getCurTime());
        entity.setStatus(StatusEnum.YES.value());
        entity.setIsDelete(IsDeleteEnum.DEFAULT.value());

        organizationMapper.insertSelective(entity);

        return organizationMapper.findOrganizationById(entity.getId());

    }

    public OrganizationEntity findGroupByOrgCodeAndUserType(String orgCode, Integer userType) {
        return organizationMapper.findGroupByOrgCodeAndUserType(orgCode, userType);
    }

    public void updateOrganizationById(OrganizationEntity organization) {

        int count = organizationMapper.updateById(organization);

    }

    public OrganizationEntity getOrganization(String orgCode, Integer userType, String userId) {
        return organizationMapper.findGroupByOrgCodeAndUserTypeAndUserId(orgCode, userType, userId);
    }

    public OrganizationEntity findGroupByOrgCode(String orgCode) {
        return organizationMapper.findGroupByOrgCode(orgCode);
    }

    public OrganizationEntity findGroupById(String userId) {
        return organizationMapper.findGroupById(userId);
    }


}

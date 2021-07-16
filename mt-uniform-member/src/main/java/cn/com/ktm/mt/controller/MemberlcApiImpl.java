package cn.com.ktm.mt.controller;


import cn.com.ktm.mt.mapper.OrganizationMapper;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.entity.GroupAduitEntity;
import cn.com.ktm.mt.model.entity.PartnerEntity;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.message.member.announcement.AnnouncementBean;
import cn.com.ktm.mt.model.message.member.announcement.AnnouncementListResponseBody;
import cn.com.ktm.mt.model.message.member.announcement.QueryAnnouncementListRequest;
import cn.com.ktm.mt.model.message.member.announcement.QueryAnnouncementListResponse;
import cn.com.ktm.mt.model.message.member.announcement.info.AnnoInfoRespBody;
import cn.com.ktm.mt.model.message.member.announcement.info.QueryAnnoInfoRequest;
import cn.com.ktm.mt.model.message.member.announcement.info.QueryAnnoInfoResponse;
import cn.com.ktm.mt.model.message.member.sync.SyncMTGroupAduitBean;
import cn.com.ktm.mt.model.message.member.sync.SyncMTGroupAuditRequest;
import cn.com.ktm.mt.model.message.member.sync.SyncMTGroupAuditResponse;
import cn.com.ktm.mt.model.message.partnerchannel.SyncMTChannelStatusResponse;
import cn.com.ktm.mt.model.util.HttpClientUtils;
import cn.com.ktm.mt.model.util.utils.JsonUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@RestController
@Log4j
public class MemberlcApiImpl {


    @Autowired
    private OrganizationMapper om;

   /* @Autowired
    private ChannelApi channelApi;*/


    @RequestMapping(value = "/syncMTGroupAduit", method = RequestMethod.POST, consumes = "application/json")
    public OtaResponse syncMTGroupAduit(@RequestBody SyncMTGroupAduitBean sb) {
        OtaResponse sresponse = new SyncMTChannelStatusResponse();
        SyncMTGroupAuditRequest srequest = new SyncMTGroupAuditRequest();
        Properties prop = new Properties();
        // prop.load(new FileInputStream(new File("C:\\jdbc.properties")));
        try {
            prop.load(MemberApiImpl.class.getClassLoader().getResourceAsStream("MemberUrl.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String partnerId = prop.getProperty("url.partnerId");
      //  PartnerEntity partner = channelApi.getPartner(partnerId);

        try {
            srequest.setPartnerId(partnerId);
            srequest.setChannelId(Long.valueOf(sb.getChannelId()));
            srequest.setBody(new GroupAduitEntity(sb.getUserType(), sb.getPhone(), sb.getUserId(), sb.getOrgCode(), sb.getAuditStatus(), sb.getAuditStatusChangedTime(), sb.getStatus()));
            //向MT发送请求同步的数据
            System.out.println(JsonUtil.toJson(srequest));
           // String s = HttpClientUtils.sendPost2Body(partner.getOrgAuditNotifyUrl(), JsonUtil.toJson(srequest), "UTF-8", "application/json", partner.getClientId(), partner.getPublicKey(),partner.getId());
           // sresponse = JsonUtil.fromString(s, SyncMTGroupAuditResponse.class);
        } catch (Exception e) {
            sresponse = OtaResponse.fail(ResponseConsts.ERROR, e.getMessage(), srequest.getPartnerId(), srequest.getChannelId());
        }
        System.out.println(JsonUtil.toJson(sresponse));
        return sresponse;
    }


    @RequestMapping(value = "/queryAnnouncementList", method = RequestMethod.POST, consumes = "application/json")
    public OtaResponse queryAnnouncementList(@RequestBody QueryAnnouncementListRequest sb) {
        System.out.println(JsonUtil.toJson(sb));
        OtaResponse sresponse = new QueryAnnouncementListResponse();
        if (sb == null) {
            sresponse.setDescribe("请求错误");
            sresponse.setCode(802);
            return sresponse;
        } else if (sb.getPartnerId() == null || sb.getChannelId() == null) {
            sresponse.setDescribe("partnerId和channelId不能为空");
            sresponse.setCode(802);
            return sresponse;
        } else if (sb.getBody() == null) {
            sresponse.setCode(802);
            sresponse.setDescribe("body为NUll");
            return sresponse;
        } else if ((sb.getBody().getAnnouncementType() == null ? 0 : sb.getBody().getAnnouncementType()) > 3 || (sb.getBody().getAnnouncementType() == null ? 0 : sb.getBody().getAnnouncementType()) < 0) {
            sresponse.setCode(803);
            sresponse.setDescribe("公告类型⽆效");
            return sresponse;
        } else if (sb.getBody().getPageIndex() == null || sb.getBody().getPageSize() == null) {
            sresponse.setCode(802);
            sresponse.setDescribe("页码或者页数量为NUll");
            return sresponse;
        } else {
            try {
                sresponse.setPartnerId(sb.getPartnerId());
                sresponse.setChannelId(sb.getChannelId());
                sresponse.setCode(200);
                sresponse.setDescribe("成功");
                Integer size = sb.getBody().getPageSize();
                Integer index = sb.getBody().getPageIndex();
                List<AnnouncementBean> alist = om.getAnnouncementList(size, (index - 1) * size);
                AnnouncementListResponseBody ab = new AnnouncementListResponseBody(alist.size(), index, size, alist);
                sresponse.setBody(ab);
            } catch (Exception e) {
                sresponse = OtaResponse.fail(ResponseConsts.ERROR, e.getMessage(), sb.getPartnerId(), sb.getChannelId());
                sresponse.setCode(801);
                sresponse.setDescribe("异常错误");
            }
            System.out.println(JsonUtil.toJson(sresponse));
            return sresponse;
        }
    }


    @RequestMapping(value = "/queryAnnouncementInfo", method = RequestMethod.POST, consumes = "application/json")
    public OtaResponse queryAnnouncementInfo(@RequestBody QueryAnnoInfoRequest sb) {
        OtaResponse sresponse = new QueryAnnoInfoResponse();
        if (sb == null) {
            sresponse.setDescribe("请求错误");
            sresponse.setCode(802);
            return sresponse;
        } else if (sb.getPartnerId() == null || sb.getPartnerId() == null) {
            sresponse.setDescribe("partnerId和channelId不能为空");
            sresponse.setCode(802);
            return sresponse;
        } else if (sb.getBody() == null) {
            sresponse.setCode(802);
            sresponse.setDescribe("body为NUll");
            return sresponse;
        } else if (sb.getBody().getNoticeId() == null || sb.getBody().getNoticeId().equals("")) {
            sresponse.setCode(804);
            sresponse.setDescribe("公告⽆效");
            return sresponse;
        } else {
            try {
                sresponse.setPartnerId(sb.getPartnerId());
                sresponse.setChannelId(sb.getChannelId());
                sresponse.setCode(200);
                sresponse.setDescribe("成功");
                String noticeId = sb.getBody().getNoticeId();
                AnnoInfoRespBody announcementInfo = om.getAnnouncementInfo(noticeId);
                sresponse.setBody(announcementInfo);
            } catch (Exception e) {
                sresponse = OtaResponse.fail(ResponseConsts.ERROR, e.getMessage(), sb.getPartnerId(), sb.getChannelId());
                sresponse.setCode(801);
                sresponse.setDescribe("异常错误");
            }
            System.out.println(JsonUtil.toJson(sresponse));
            return sresponse;
        }
    }
}

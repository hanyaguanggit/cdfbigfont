package cn.com.ktm.mt.controller;

import cn.com.ktm.mt.model.exception.AssertError;
import cn.com.ktm.mt.model.home.request.SiteStructureHomeRequest;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.module.CsSiteStructureModule;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j
public class SiteStructureHomeController {

    @Autowired
    private CsSiteStructureModule csSiteStructureModule;

    /**
     * 首页信息请求
     */
    @RequestMapping(path = "/member/findhome", method = RequestMethod.POST, consumes = "application/json")
    public OtaResponse findCsSiteStructureHome(@RequestBody SiteStructureHomeRequest request) {
        OtaResponse process = new OtaResponse();

        try {
            process = csSiteStructureModule.getHomeInfo(request);

        } catch (AssertError e) {
              process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(), request.getPartnerId(), request.getChannelId());
        }
        return process;

    }
}

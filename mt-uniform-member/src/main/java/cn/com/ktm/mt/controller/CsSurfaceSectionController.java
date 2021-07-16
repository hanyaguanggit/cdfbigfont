package cn.com.ktm.mt.controller;

import cn.com.ktm.mt.model.exception.AssertError;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.section.request.CsSurfaceSectionRequest;
import cn.com.ktm.mt.module.CsSurfaceSectionModule;
import cn.com.ktm.mt.module.CsSecurityUserModule;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class CsSurfaceSectionController {

    @Autowired
    private CsSurfaceSectionModule csSurfaceSectionModule;

    Logger logger = LoggerFactory.getLogger(CsSecurityUserModule.class);

    @PostMapping(value = "/section/add", consumes = "application/json")
    public OtaResponse findMenuByUserId(@RequestBody CsSurfaceSectionRequest request) {
        OtaResponse process = new OtaResponse<>();
        try {
            process = csSurfaceSectionModule.addSection(request);
        } catch (AssertError e) {
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(),null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return process;
    }
}

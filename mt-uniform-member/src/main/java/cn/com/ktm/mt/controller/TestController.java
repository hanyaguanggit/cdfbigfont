package cn.com.ktm.mt.controller;

import cn.com.ktm.mt.model.cssitestructure.request.CsSiteStuctureRequest;
import cn.com.ktm.mt.model.cssitestructure.request.SiteStructureHomeRequest;
import cn.com.ktm.mt.model.exception.AssertError;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.CsSiteStructure;
import cn.com.ktm.mt.module.CsSiteStructureModule;
import cn.com.ktm.mt.service.CsSiteStructureService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 陈臻 on 2017/8/3.
 */
@RestController
public class TestController {

    @Autowired
    private CsSiteStructureModule csSiteStructureModule;
    
    @RequestMapping("/monitor")
    public String  test(){
        return "ok";
    }


}

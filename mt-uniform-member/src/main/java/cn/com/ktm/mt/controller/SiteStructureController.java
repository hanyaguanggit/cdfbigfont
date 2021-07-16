package cn.com.ktm.mt.controller;

import cn.com.ktm.mt.model.exception.AssertError;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.sitestructure.request.CsSiteStuctureListRequest;
import cn.com.ktm.mt.model.sitestructure.request.CsSiteStuctureRequest;
import cn.com.ktm.mt.module.CsSiteStructureModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteStructureController {

    @Autowired
    private CsSiteStructureModule csSiteStructureModule;

   /**
     * @return 根据parentId查询子网站结构
     */
   /* @PostMapping(path = "/member/findByParentId", consumes = "application/json")
    public OtaResponse findCsSiteStructure(@RequestBody CsSiteStuctureRequest csSiteStuctureRequest) {
        OtaResponse process = new OtaResponse();
        try {
           // process = csSiteStructureModule.findCsSiteStructureByParentId(csSiteStuctureRequest);

        } catch (AssertError e) {
            //  process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(), request.getPartnerId(), request.getChannelId());
        }
        return process;

    }*/


    /**
     * hyg
     * 根据网站结构id查找结构信息
     * @return
     */
    @PostMapping(path = "/member/findcs", consumes = "application/json")
    public OtaResponse findCsSiteStructure(@RequestBody CsSiteStuctureRequest csSiteStuctureRequest) {
        OtaResponse process = new OtaResponse();
        try {
            process = csSiteStructureModule.getInfo(csSiteStuctureRequest);

        } catch (AssertError e) {
            e.printStackTrace();
              process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(),null,null);
        }
        return process;

    }

    /**
     * hyg
     * 根据网站父id查找所有子结构信息
     * @param csSiteStuctureRequest
     * @return
     */
    @PostMapping(path = "/member/findcsByParent", consumes = "application/json")
    public OtaResponse findCsSiteStructureByparentId(@RequestBody CsSiteStuctureListRequest csSiteStuctureRequest) {
        OtaResponse process = new OtaResponse();
        try {
            process  = csSiteStructureModule.findCsSiteStructureByParentId(csSiteStuctureRequest);

        } catch (AssertError e) {
            e.printStackTrace();
            process = OtaResponse.fail(e.getErrorCode().getCode(), e.getMessage(),null,null);
        }
        return process;

    }



}

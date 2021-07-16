package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.CsSurfacePictureLinkMapper;
import cn.com.ktm.mt.model.CsSurfacePictureLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsSurfacePictureLinkService {
    @Autowired
    private CsSurfacePictureLinkMapper csSurfacePictureLinkMapper;

    public int addCsSurfacePictureLink (CsSurfacePictureLink csSurfacePictureLink){
        int add = csSurfacePictureLinkMapper.insertSelective(csSurfacePictureLink);
        return add > 0 ? 1 : 0;
    }
}

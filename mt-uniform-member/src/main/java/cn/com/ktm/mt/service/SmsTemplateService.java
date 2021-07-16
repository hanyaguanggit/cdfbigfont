package cn.com.ktm.mt.service;

import cn.com.ktm.mt.mapper.SmsTemplateMapper;
import cn.com.ktm.mt.model.entity.SmsTempletEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsTemplateService {
    @Autowired
    private SmsTemplateMapper smsTemplateMapper;


    public List<SmsTempletEntity> findAllSmsTemplate() {
        return smsTemplateMapper.findAllSmsTemplate();
    }
}

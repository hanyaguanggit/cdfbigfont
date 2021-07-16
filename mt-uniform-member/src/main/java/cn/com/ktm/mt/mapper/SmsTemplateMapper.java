package cn.com.ktm.mt.mapper;

import cn.com.ktm.mt.model.entity.SmsTempletEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuanpeng
 *
 */
@Repository
public interface SmsTemplateMapper {
    List<SmsTempletEntity> findAllSmsTemplate();
}

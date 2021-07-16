package cn.com.ktm.mt.model.message.member.outcountnofity;


import lombok.Data;

import java.util.Date;

/**
 * @author yuanpeng
 * @date 2020/6/2
 */
@Data
public class LeaveParkBean {
    private Integer count;

    private String partnerId;

    private String tenantId;

    private String tenantName;

    private String outTime;
}

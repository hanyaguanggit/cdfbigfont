package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiLogEntity {
    private Long id;

    private String partnerId;

    private Long channelId;

    private String requestJ;

    private String ip;

    private String responseJ;

    private Long createTime;

    private Long responsTime;

}
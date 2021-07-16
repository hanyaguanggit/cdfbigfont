package cn.com.ktm.mt.model.message.member.sync;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SyncMTGroupAduitBean {
    private Integer channelId;
    private Integer userType;
    private String phone;
    private String userId;
    private String orgCode;
    private Long auditStatusChangedTime ;
    private Integer status;

    public SyncMTGroupAduitBean(Integer channelId, Integer userType, String phone, String userId, String orgCode, Integer status, Integer auditStatus) {
        this.channelId = channelId;
        this.userType = userType;
        this.phone = phone;
        this.userId = userId;
        this.orgCode = orgCode;
        this.status = status;
        this.auditStatus = auditStatus;
    }

    private Integer auditStatus;
}

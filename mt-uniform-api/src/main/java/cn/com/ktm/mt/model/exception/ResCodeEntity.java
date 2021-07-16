package cn.com.ktm.mt.model.exception;

import lombok.Data;

/**
 *
 */
@Data
public class ResCodeEntity {

    private Integer code;
    private Long channelId;
    private String partnerId;
    private String fileName;
    private String desc;
    @Override
    public String toString() {
        return "ReturnCode{" + "code=" + code + ", desc='" + desc + '\'' + ", partnerId='" + partnerId + '\'' +", channelId='" + channelId + '\'' +", fileName='" + fileName + '\'' +'}';
    }

    public ResCodeEntity(Integer code, String desc) {
        super();
        this.code = code;
        this.desc = desc;
    }
    
    public ResCodeEntity(Integer code, String desc,String partnerId,Long channelId,String fileName) {
        super();
        this.code = code;
        this.desc = desc;
        this.partnerId = partnerId;
        this.channelId = channelId;
        this.fileName = fileName;
    }

}

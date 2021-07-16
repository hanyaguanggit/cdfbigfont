package cn.com.ktm.mt.model.message.member.groupreserve;

import cn.com.ktm.mt.model.message.Body;
import lombok.Data;

@Data
public class GroupReserveOrderResponseBody implements Body {
    private String userId;
    private String orgCode;
    private String appointmentId;
    private Integer appointmentStatus;
    @Override
    public void valid() {

    }
}

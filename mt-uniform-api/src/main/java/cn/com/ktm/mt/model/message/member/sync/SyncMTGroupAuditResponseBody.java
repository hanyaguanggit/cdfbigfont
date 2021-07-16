package cn.com.ktm.mt.model.message.member.sync;

import cn.com.ktm.mt.model.message.Body;
import cn.com.ktm.mt.model.message.OtaResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyncMTGroupAuditResponseBody implements Body {
    private int userType;
    private String userId;

    @Override
    public void valid() {
        //
    }
}

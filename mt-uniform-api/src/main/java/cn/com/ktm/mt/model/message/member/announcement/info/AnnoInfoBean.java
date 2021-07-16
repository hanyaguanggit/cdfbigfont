package cn.com.ktm.mt.model.message.member.announcement.info;

import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  AnnoInfoBean implements Body {
    @Override
    public void valid() {

    }
    private  String noticeId;
}

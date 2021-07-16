package cn.com.ktm.mt.model.message.member.announcement.info;

import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnoInfoRespBody implements Body {
    @Override
    public void valid() {

    }
    private String noticeId;
    private String title;
    private String content;
    private Integer announcementType;
    private String date;
}

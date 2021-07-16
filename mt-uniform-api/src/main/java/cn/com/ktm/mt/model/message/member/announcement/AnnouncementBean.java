package cn.com.ktm.mt.model.message.member.announcement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementBean {
    private  Integer announcementType;
    private  String noticeId;
    private  String title;
    private  String date;
}

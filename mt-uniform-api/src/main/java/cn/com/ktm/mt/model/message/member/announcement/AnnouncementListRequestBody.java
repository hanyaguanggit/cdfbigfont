package cn.com.ktm.mt.model.message.member.announcement;

import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementListRequestBody  implements Body {
    @Override
    public void valid() {

    }
    private Integer pageIndex;
    private Integer pageSize;
    private Integer announcementType;
}

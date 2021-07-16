package cn.com.ktm.mt.model.message.member.announcement;

import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementListResponseBody implements Body {
    @Override
    public void valid() {

    }
    private  Integer totalCount;
    private  Integer pageIndex;
    private  Integer pageSize;
    private List<AnnouncementBean>  noticeList = new ArrayList<>();
}

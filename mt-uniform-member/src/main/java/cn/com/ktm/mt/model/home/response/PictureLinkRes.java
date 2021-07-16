package cn.com.ktm.mt.model.home.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PictureLinkRes extends BaseHomeRes {

    private Integer picture;
    private String  pictureUrl;
}

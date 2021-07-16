package cn.com.ktm.mt.model.home.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextLinkRes extends BaseHomeRes{
    //文字链接
    private String textName;
    private String textUrl;
}

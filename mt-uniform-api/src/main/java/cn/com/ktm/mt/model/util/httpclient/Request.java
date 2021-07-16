package cn.com.ktm.mt.model.util.httpclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    private Long channel_id;

    private String biz_content;

    private String sign;

    private String token;

}

package cn.com.ktm.mt.model.util.httpclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 请求第三方统一入口，
 * 此方法对公共参数进行设置例如Header，sgin 等信息。
 */
@Component
public class ClientSender {

    @Autowired
    private MessageSender sender;


    public <U extends Body, T extends Body> Response<U> send(String sendUrl, T body, Class<U> clazz, Long channel_id) throws Exception {

        return sender.send(sendUrl, body, clazz,channel_id);
    }

}

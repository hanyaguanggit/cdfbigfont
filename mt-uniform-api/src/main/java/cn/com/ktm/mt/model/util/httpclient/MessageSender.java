package cn.com.ktm.mt.model.util.httpclient;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;


@Component
public class MessageSender {

    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    public <U extends Body, T extends Body> Response<U> send(String sendUrl, T reqBody, Class<U> resClazz,Long channel_id) throws Exception {
        String requestJson = null;
        try {
            requestJson = MessageWrapper.from(reqBody,channel_id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        logger.debug("发送消息: {}", requestJson);
        Response<U> post = null;
        String response;
        response = RestClient.postForObject(sendUrl, requestJson, String.class);
        logger.debug("接收到消息: {}", response);
        post = parse(response, resClazz);

        return post;
    }


    public <U extends Body> Response<U> parse(String json, Class<U> clazz) {
        Response uResponse = JSON.parseObject(json, new TypeReference<Response<U>>(clazz) {
                }
        );
        return uResponse;
    }
}
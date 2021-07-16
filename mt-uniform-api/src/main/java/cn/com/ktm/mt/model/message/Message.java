package cn.com.ktm.mt.model.message;




/**
 * 通讯消息接口.
 *
 * <p>
 *  消息按传送方向分为4类:
 *  {@link Request}表示第三方发送的请求消息,传送方向为: 第三方->中心
 *  {@link Reponse}表示{@link Request}消息的响应消息,传送方向为: 中心-> 第三方

 * </p>
 */
public interface Message {

}

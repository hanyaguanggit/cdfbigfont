package cn.com.ktm.mt.model.message;

/**
 *  check消息的接口.
 * 
 * @author wjq
 *
 * @param <T> 包体类
 */
public interface CommonMessage<T extends Body> extends Message {

	/**
	 * @return the header
	 */
	public  String getPartnerId();

	/**
	 * @param partnerId the header to set
	 */
	public void setPartnerId(String partnerId);




	/**
	 * @return the header
	 */
	public  Long getChannelId();

	/**
	 * @param channelId the header to set
	 */
	public void setChannelId(Long channelId);

	/**
	 * @return the responseBody
	 */
	public T getBody();

	/**
	 * @param body the responseBody to set
	 */
	public void setBody(T body);


	/**
	 * @return the header
	 */
	public  Integer getShopId();

	/**
	 * @param shopId the header to set
	 */
	public void setShopId(Integer shopId);

}

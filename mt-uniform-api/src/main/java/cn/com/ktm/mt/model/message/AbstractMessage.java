package cn.com.ktm.mt.model.message;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author wjq
 *
 * @param <T>
 */
public abstract class AbstractMessage<T extends Body > implements CommonMessage<T>  {
	private String partnerId;
	private Long channelId;
	private T body;


	@Override
	@JsonProperty("partnerId")
	public String getPartnerId() {
		return partnerId;
	}

	@Override
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}



    @Override
    @JsonProperty("channelId")
    public Long getChannelId() {
        return channelId;
    }

    @Override
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

	@Override
	public T getBody() {
		return body;
	}

	@Override
	public void setBody(T body) {
		this.body = body;
	}

}

package cn.com.ktm.mt.model.message;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;

public abstract class OtaRequest<T extends Body > extends AbstractMessage<T> implements Request {
	@Override
	public void valid() {
	  Assert.notNull(this.getPartnerId(), ResponseConsts.PARAM_ERROR);
	  Assert.notNull(this.getChannelId(), ResponseConsts.PARAM_ERROR);
	}

}

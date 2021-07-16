package cn.com.ktm.mt.model.message;


/**
 * 编码消息异常.
 */
public class MarshalException extends RuntimeException {

	private static final long serialVersionUID = 4974471066793218836L;

	public MarshalException() {
		super();
	}

	public MarshalException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MarshalException(String message, Throwable cause) {
		super(message, cause);
	}

	public MarshalException(String message) {
		super(message);
	}

	public MarshalException(Throwable cause) {
		super(cause);
	}

}

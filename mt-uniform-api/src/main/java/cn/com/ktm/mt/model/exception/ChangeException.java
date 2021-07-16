package cn.com.ktm.mt.model.exception;

public class ChangeException extends Exception {
	private static final long serialVersionUID = -1432127686007621702L;

	public ChangeException() {
		super();
	}

	public ChangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ChangeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChangeException(String message, Exception cause) {
		super(message, cause);
	}

	public ChangeException(String message) {
		super(message);
	}

	public ChangeException(Throwable cause) {
		super(cause);
	}

}

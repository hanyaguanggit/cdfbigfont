package cn.com.ktm.mt.model.exception;

/**
 * Created by wjq on 2018/3/5.
 */
public class BaseException extends RuntimeException  {

        private static final long serialVersionUID = -1432127686007621702L;

        public BaseException() {
            super();
        }

        public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }

        public BaseException(String message, Throwable cause) {
            super(message, cause);
        }

        public BaseException(String message, Exception cause) {
            super(message, cause);
        }

        public BaseException(String message) {
            super(message);
        }

        public BaseException(Throwable cause) {
            super(cause);
        }

}

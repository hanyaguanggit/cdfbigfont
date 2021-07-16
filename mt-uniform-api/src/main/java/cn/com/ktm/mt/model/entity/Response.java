package cn.com.ktm.mt.model.entity;

 
import lombok.Data;

@Data
public class Response<T> implements Resp {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1913648323586050522L;
	protected String code = "200";
    protected String errorMsg = "";

    /**
     * 参数错误码
     */
    public static final String PARAMETER_ERROR_CODE = "400";

    /**
     * 业务错误码
     */
    public static final String BUSINESS_ERROR_CODE = "500";

    protected T data = null;

    public Response() {
    }

    public Response(T data) {
        this.data = data;
    }

    public Response(String code, String errorMsg, T data) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public static Response error(String errorMsg) {
        return new Response(BUSINESS_ERROR_CODE, errorMsg, null);
    }

    public static Response errorWithCode(String code, String errorMsg) {
        return new Response(code, errorMsg, null);
    }

    public static <T> Response ok(T t) {
        return new Response(t);
    }

}
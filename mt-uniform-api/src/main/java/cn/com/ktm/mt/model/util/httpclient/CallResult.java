package cn.com.ktm.mt.model.util.httpclient;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * Created by Vagile on 2016-10-17.
 */
@Data
public class CallResult<T> {

    protected int code;           // 结果状态码

    protected long count;           // 分页时的总页数

    protected T data;           // 数据：根据具体的业务来定义与封装

    protected String desc;           // 描述信息：该信息可以直接显示给用户。


    public CallResult() {

    }


    @JsonIgnore
    @JSONField(serialize = false)
    public boolean isNotSuccess() {

        return !isSuccess();
    }

    /**
     * 根据响应状态码判断请求处理是否成功
     *
     * @return boolean
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public boolean isSuccess() {

        /* 0：处理成功响应码 */
        return code == 0;
    }

    public CallResult(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public CallResult(T data, long count) {
        this.data = data;
        this.count = count;
    }

    public CallResult(int code, String desc, String error) {
        this(code, desc);
    }

    public CallResult(int code, String desc, T data) {
        this(code, desc);
        this.data = data;
    }

    public CallResult(int code, String desc, String error, T data) {
        this(code, desc, error);
        this.data = data;
    }
}

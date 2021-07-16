package cn.com.ktm.mt.model.message;



import cn.com.ktm.mt.model.constant.ResponseConsts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtaResponse<T> implements Response {

    protected T body; // 数据：根据具体的业务来定义与封装
    protected int code; // 结果状态码
    protected String describe; // 描述信息：该信息可以直接显示给用户。
    protected String partnerId; // 异常信息：该信息可以包含具体的错误信息，供开发人员使用。
    protected Long  channelId; // 异常信息：该信息可以包含具体的错误信息，供开发人员使用。
    protected int sectionId;// 临时变量 模块id

    public static OtaResponse fail(int code, String describe, String partnerId,Long channelId) {
        OtaResponse otaResponse = new OtaResponse();
        otaResponse.setCode(code);
        otaResponse.setDescribe(describe);
        otaResponse.setPartnerId(partnerId);
        otaResponse.setChannelId(channelId);
        return otaResponse;

    }


    public static OtaResponse Ok( String partnerId,Long channelId) {
        OtaResponse otaResponse = new OtaResponse();
        otaResponse.setCode(ResponseConsts.SUCCESS);
        otaResponse.setDescribe("SUCCESS");
        otaResponse.setPartnerId(partnerId);
        otaResponse.setChannelId(channelId);
        return otaResponse;
    }


    public static OtaResponse Ok( String partnerId,Long channelId,Body body) {
        OtaResponse otaResponse = new OtaResponse();
        otaResponse.setCode(ResponseConsts.SUCCESS);
        otaResponse.setBody(body);
        otaResponse.setDescribe("SUCCESS");
        otaResponse.setPartnerId(partnerId);
        otaResponse.setChannelId(channelId);
        return otaResponse;
    }

}


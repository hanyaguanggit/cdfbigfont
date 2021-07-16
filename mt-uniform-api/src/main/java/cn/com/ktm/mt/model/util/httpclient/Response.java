package cn.com.ktm.mt.model.util.httpclient;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<U extends Body> {
    private Integer code;
    private U result;
    private Integer type;
    private String error;
    private Integer system_type;
    private String service_ip;
    private String service_port;
    private String remote_ip;
    private String trace_identifier;
    private String rsp_datetime;

}

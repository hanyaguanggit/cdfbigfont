package cn.com.ktm.mt.model.util.utils.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import cn.com.ktm.mt.model.util.utils.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HTTP工具类 Created by gaocl on 16-4-18.
 */
@Component
public class HttpClientUtil {


    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static ThreadLocal<String> IP_ADDR = new ThreadLocal<>();

    // 传输超时时间，默认10秒
    private static final int SOCKET_TIMEOUT = 10000;

    // 连接超时时间，默认10秒
    private static final int CONNECT_TIMEOUT = 10000;


    public static Response get(String url) throws Exception {

        Response            response;
        CloseableHttpClient httpclient = HttpClients.custom().build();

        // 根据默认超时限制初始化requestConfig
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT).build();
        try {
            HttpGet httpget = new HttpGet(url);
            httpget.setConfig(requestConfig);

            CustomResponseHandler responseHandler = new CustomResponseHandler();

            response = httpclient.execute(httpget, responseHandler);

        } finally {
            httpclient.close();
        }

        return response;
    }

    public static String post(String url, List<NameValuePair> params) throws Exception {

        Response            response;
        CloseableHttpClient httpclient = HttpClients.custom().build();
        // 根据默认超时限制初始化requestConfig
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT).build();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            CustomResponseHandler responseHandler = new CustomResponseHandler();

            response = httpclient.execute(httpPost, responseHandler);

            if(response.isStatusOK()){
                return response.getResponseBody();
            }else{
                throw new Exception("请求返回状态码错误"+response.getStatus()+"消息{}"+response.getResponseBody());
            }
        } finally {
            httpclient.close();
        }

    }

    /**
     * POST请求
     *
     * @param url
     *         请求地址
     * @param data
     *         消息体数据
     * @param connectTimeout
     *         连接超时时间
     *
     * @return Response
     *
     * @throws Exception
     */
    public static String post(String url, Map<String, String> data, int connectTimeout)
            throws Exception {
        return post(url, data, null, connectTimeout, SOCKET_TIMEOUT);
    }

    /**
     * POST请求
     *
     * @param url
     *         请求地址
     * @param data
     *         消息体数据
     * @param header
     *         请求头
     * @param connectTimeout
     *         连接超时时间
     *
     * @return Response
     *
     * @throws Exception
     */
    public static String post(String url, Map<String, String> data, Map<String, String> header, int connectTimeout)
            throws Exception {
        return post(url, data, header, connectTimeout, SOCKET_TIMEOUT);
    }

    /**
     * POST请求。 注：未设置MINE类型，编码格式，采用默认的设置
     *
     * @param url
     *         请求地址
     * @param data
     *         消息体数据
     *
     * @return Response
     *
     * @throws Exception
     */
    public static Response post(String url, String data) throws Exception {

        return post(url, data, null, null, CONNECT_TIMEOUT, SOCKET_TIMEOUT);
    }

    /**
     * POST请求。注：未设置MINE类型，编码格式，采用默认的设置
     *
     * @param url
     *         请求地址
     * @param data
     *         消息体数据
     * @param header
     *         请求头
     *
     * @return Response
     *
     * @throws Exception
     */
    public static Response post(String url, String data, Map<String, String> header) throws Exception {

        return post(url, data, header, null, CONNECT_TIMEOUT, SOCKET_TIMEOUT);
    }

    /**
     * POST请求
     *
     * @param url
     *         请求地址
     * @param data
     *         消息体数据
     * @param contentType
     *         MIME类型
     *
     * @return Response
     *
     * @throws Exception
     */
    public static Response post(String url, String data, ContentType contentType) throws Exception {

        return post(url, data, null, contentType, CONNECT_TIMEOUT, SOCKET_TIMEOUT);
    }

    /**
     * POST请求
     *
     * @param url
     *         请求地址
     * @param data
     *         消息体数据
     * @param header
     *         请求头
     * @param contentType
     *         MIME类型
     *
     * @return Response
     *
     * @throws Exception
     */
    public static Response post(String url, String data, Map<String, String> header, ContentType contentType)
            throws Exception {
        return post(url, data, header, contentType, CONNECT_TIMEOUT, SOCKET_TIMEOUT);
    }

    /**
     * POST请求
     *
     * @param url
     *         请求地址
     * @param data
     *         消息体数据
     * @param header
     *         请求头
     * @param contentType
     *         MIME类型
     * @param connectTimeout
     *         连接超时时间
     *
     * @return Response
     *
     * @throws Exception
     */
    public static Response post(String url, String data, Map<String, String> header, ContentType contentType, int connectTimeout)
            throws Exception {
        return post(url, data, header, contentType, connectTimeout, SOCKET_TIMEOUT);
    }

    /**
     * POST请求
     *
     * @param url
     *         请求地址
     * @param data
     *         消息体数据
     * @param header
     *         请求头
     * @param contentType
     *         MIME类型
     * @param connectTimeout
     *         连接超时时间
     * @param socketTimeout
     *         传输超时时间
     *
     * @return Response
     *
     * @throws Exception
     */
    public static Response post(String url, String data, Map<String, String> header, ContentType contentType, int connectTimeout, int socketTimeout)
            throws Exception {
        Response            response;
        CloseableHttpClient httpclient = HttpClients.createDefault();

        /* 根据默认超时限制初始化requestConfig */
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();

        try {
            HttpPost httpPost = new HttpPost(url);

            httpPost.setConfig(requestConfig);

            /* 设置请求头 */
            if (null != header) {
                header.forEach((k, v) -> {
                    Header h = new BasicHeader(k, v);
                    httpPost.setHeader(h);
                });
            }

            /* 消息体格式化 */
            StringEntity stringEntity;
            if (null != contentType) {
                stringEntity = new StringEntity(data, contentType);
            } else {
                stringEntity = new StringEntity(data);
            }
            httpPost.setEntity(stringEntity);

            CustomResponseHandler responseHandler = new CustomResponseHandler();

            response = httpclient.execute(httpPost, responseHandler);
        } finally {
            httpclient.close();
        }

        return response;
    }

    /**
     * POST请求
     *
     * @param url
     *         请求地址
     * @param data
     *         消息体数据
     * @param header
     *         请求头
     * @param connectTimeout
     *         连接超时时间
     * @param socketTimeout
     *         传输超时时间
     *
     * @return Response
     *
     * @throws Exception
     */
    public static String post(String url, Map<String, String> data, Map<String, String> header, int connectTimeout, int socketTimeout)
            throws Exception {
        Response            response;
        CloseableHttpClient httpclient = HttpClients.createDefault();

        /* 根据默认超时限制初始化requestConfig */
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();

        try {
            HttpPost httpPost = new HttpPost(url);

            httpPost.setConfig(requestConfig);

            /* 设置请求头 */
            if (null != header) {
                header.forEach((k, v) -> {
                    Header h = new BasicHeader(k, v);
                    httpPost.setHeader(h);
                });
            }

            /* 设置消息体 */
            List<NameValuePair> params = new ArrayList<>();
            if (null != data) {
                data.forEach((key, value) -> params.add(new BasicNameValuePair(key, value)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

            CustomResponseHandler responseHandler = new CustomResponseHandler();

            response = httpclient.execute(httpPost, responseHandler);
            if(response.isStatusOK()){
                return response.getResponseBody();
            }else{
                throw new Exception("请求返回状态码错误"+response.getStatus()+"消息{}"+response.getResponseBody());
            }
        } finally {
            httpclient.close();
        }

    }

    /**
     * 响应结果
     */
    public static class Response {

        private Integer status;
        private String  responseBody;

        public boolean isStatusNotOK() {

            return !isStatusOK();
        }

        /**
         * 根据http请求响应状态码是否请求成功。true：请求成功。false：请求失败
         *
         * @return boolean
         */
        public boolean isStatusOK() {

            return status != null && status == 200;
        }

        public Integer getStatus() {
            return status;
        }

        void setStatus(Integer status) {
            this.status = status;
        }

        public String getResponseBody() {
            return responseBody;
        }

        void setResponseBody(String responseBody) {
            this.responseBody = responseBody;
        }
    }

    static class CustomResponseHandler implements ResponseHandler<Response> {

        public int status = 200; // 状态值:默认成功

        @Override
        public Response handleResponse(final HttpResponse response) throws IOException {
            status = response.getStatusLine().getStatusCode();
            Response res = new Response();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();

                res.setResponseBody(entity != null ? EntityUtils.toString(entity, "utf-8") : null);
            } else {
                return null;
            }
            res.setStatus(status);
            return res;
        }
    }




    public static void setIpAddr(HttpServletRequest request) {
        String ip = getIpAddr(request);

        if (StringUtils.isEmpty(ip)) {
            logger.info("ip is empty, request={}", JsonUtil.toJson(request));
            return;
        }

        IP_ADDR.set(ip);
        logger.debug("setIp() is OK, ip={}", ip);
    }

    public static String getIpAddr() {
        return IP_ADDR.get();
    }

    /**
     * 获取IP地址信息
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null)
            return null;
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("Proxy-Client-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("HTTP_CLIENT_IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getRemoteAddr();
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip))
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException ignored) {
            }
        return ip;
    }

    /**
     * 获取useragent
     *
     * @param request
     * @return
     */
    public static String getUserAgent(HttpServletRequest request) {
        if (request == null)
            return null;
        return request.getHeader("User-Agent");
    }

    public static String getRequestBody(HttpServletRequest request) throws Exception {
        InputStream is = request.getInputStream();
        byte bytes[] = new byte[request.getContentLength()];
        is.read(bytes);
        String body = new String(bytes, request.getCharacterEncoding());
        return body;
    }

    /**
     * URL编码
     *
     * @param str
     * @return
     */
    public static String escape(String str) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(str.length() * 6);
        for (i = 0; i < str.length(); i++) {
            j = str.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j)
                    || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    /**
     * URL解码
     *
     * @param str
     * @return
     */
    public static String unescape(String str) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(str.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < str.length()) {
            pos = str.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (str.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(str
                            .substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(str
                            .substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(str.substring(lastPos));
                    lastPos = str.length();
                } else {
                    tmp.append(str.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }



    private static String defaultContentEncoding = "UTF-8";

    /**
     *
     * @Title: writeToStream
     * @Description: 向指定地址写入数据,并获取响应数据
     * 采用post方式请求
     * @author heguan
     * @date 2016年5月17日 上午10:33:36
     * @param @param addr 接口地址
     * @param @param info 输入字符串
     * @return String    返回类型
     * @throws
     */
    public static String writeToStream(String reqUrl, String info) {
        InputStream in = null;
        OutputStream outs = null;
        HttpURLConnection con = null;
        String result = "";
        try {
            URL url = new URL(reqUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(30000);
            con.setReadTimeout(30000);
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            outs = con.getOutputStream();
            IOUtils.write(info, outs, defaultContentEncoding);
            in = con.getInputStream();
            result = IOUtils.toString(in, defaultContentEncoding);
        } catch (Exception e) {
            logger.error("连接异常",e);
            throw new RuntimeException(e.getCause());
        } finally {
            IOUtils.closeQuietly(outs);
            IOUtils.closeQuietly(in);
            con.disconnect();
        }
        return result;
    }


}

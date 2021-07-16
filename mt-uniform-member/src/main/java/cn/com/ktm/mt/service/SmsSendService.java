package cn.com.ktm.mt.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 武连杰
 * @version 1.0
 * @date 2020/12/18 11:15
 */
@Service
public class SmsSendService {
    /**
     *   Result结果响应
     *   00 提交成功
     *   1 参数不完整，请检查所带的参数名是否都正确
     *   2 鉴权失败，一般是用户名密码不对
     *   3 号码数量超出 50 条
     *   4 发送失败；5 余额不足；6 发送内容含屏蔽词；7 短信内容超出 350 个字；72 内容被审核员屏蔽；
     *   8:OverLimit! 号码列表中没有合法的手机号码或手机号为黑名单或验证码 类每小时超过限制条数
     *   9 夜间管理，不允许一次提交超过 20 个号码
     *   10 {txt}不应当出现在提交数据中，请修改[模板类账号]（适用于 模板类帐户）
     *   11 模板匹配成功[模板类必审、免审账号]（适用于模板类帐户）
     *   12 未匹配到合适模板，已提交至审核员审核[模板类必审账号]（适 用于模板类帐户）
     *   13 未匹配到合适模板，无法下发，请修改[模板类免审账号]（适 用于模板类帐户）
     *   14 该账户没有模板[模板类账号]（适用于模板类帐户）
     *   15 操作失败[模板类账号]（适用于模板类帐户）
     *   16 模板编号格式错误
     *   02 手机号码黑名单
     *   81 手机号码错误，请检查手机号是否正确
     *   ERR IP:XX.XX.XX.XX IP 验证未通过，请联系管理员增加鉴权
     *   IP 005 余额不足
     *   0072 内容被审核员屏蔽
     *   0002 模板账户手机黑名单
     *   0081 手机号码错误，请检查手机号是否正确
     */
    @Value("${INF_URL}")
    private  String INF_URL;
    @Value("${noticename}")
    private  String username;
    @Value("${password}")
    private   String password;
    @Value("${epid}")
    private  String epid ;



    public   String SmsSend(  String phone, String message)
            throws Exception {

        // 商户唯一标示 20 位长度
        String subcode = "";
        Map<String, String> pmap = new HashMap<String, String>();
        pmap.put("username", username);
        pmap.put("password", password);
        pmap.put("phone", phone);
        pmap.put("message", URLEncoder.encode(message, "gb2312"));
        pmap.put("epid", epid);
        pmap.put("linkid", null);
        pmap.put("subcode", subcode);
        String pstr = "";
        if (pmap != null && pmap.size() > 0) {
            for (Map.Entry<String, String> entry : pmap.entrySet()) {
                pstr += "&" + entry.getKey() + "=" + entry.getValue();
            }
            pstr = pstr.substring(1);
        }
        String result = sendGet(INF_URL + "?" + pstr);
        return result;
    }

    public static String sendGet(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        BufferedReader in = null;
        try {
            HttpGet get = new HttpGet(url);
            CloseableHttpResponse httpResponse = null;
            httpResponse = httpClient.execute(get);
            try {
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    String tempStr = "";
                    in = new BufferedReader(new InputStreamReader(entity.getContent()));
                    StringBuffer content = new StringBuffer();
                    while ((tempStr = in.readLine()) != null) {
                        content.append(tempStr);
                    }
                    return content.toString();
                }
            } finally {
                httpResponse.close();
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}

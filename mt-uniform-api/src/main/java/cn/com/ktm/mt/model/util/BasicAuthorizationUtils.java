package cn.com.ktm.mt.model.util;

import javafx.util.Pair;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class BasicAuthorizationUtils {

    private static final Logger logger = LoggerFactory.getLogger(BasicAuthorizationUtils.class);

    private static final String ALGORITHM_HMAC_SHA1 = "HmacSHA1";
    public static final String HTTP_HEADER_PARTNERID = "PartnerId";
    public static final String HTTP_HEADER_DATE = "Date";
    public static final String HTTP_HEADER_AUTHORIZATION = "Authorization";
    public static final String MEITUAN_AUTH_METHOD = "MWS";
    private static final String HTTP_HEADER_TIME_ZONE = "GMT";
    private static final String HTTP_HEADER_DATE_FORMAT = "EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'z";




    /**
     * 生成请求头认证信息
     * @param uri
     *  * @param method
     * @param clientId
     * @param clientSecret
     */
    public static Pair<String, String> createSign(String method, String uri, String clientId,
                                                  String clientSecret) {
        Date sysdate = new Date();
        SimpleDateFormat df = new SimpleDateFormat(HTTP_HEADER_DATE_FORMAT, Locale.US);
        df.setTimeZone(TimeZone.getTimeZone(HTTP_HEADER_TIME_ZONE));
        String date = df.format(sysdate);
        String stringToSign = method + " " +
                uri +
                "\n" + date;


        String encoding;
        try {
            encoding = getSignature(stringToSign.getBytes(), clientSecret.getBytes());
        } catch (Exception e1) {
            logger.warn("Signature Exception occured:", e1);
            return null;
        }
        String authorization = MEITUAN_AUTH_METHOD + " " + clientId + ":" + encoding;
        Pair<String, String> pair = new Pair<>(date, authorization);
        return pair;
    }




    /**
     * 生成请求头认证信息
     * @param request
     * @param clientId
     * @param clientSecret
     */
    public static void generateAuthAndDateHeader(HttpRequestBase  request, String clientId,
                                                 String clientSecret,String partnerId) {

        Pair<String, String> pair= createSign(request.getMethod().toUpperCase(),request.getURI().getPath(),clientId,clientSecret);

        request.addHeader(HTTP_HEADER_PARTNERID, partnerId); // 请将partnerId替换为商家自身的partnerId
        request.addHeader(HTTP_HEADER_AUTHORIZATION, pair.getValue());
        request.addHeader(HTTP_HEADER_DATE, pair.getKey());
    }

    /**
     * 生产签名
     * @param data
     * @param key
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    public static String getSignature(byte[] data, byte[] key)
            throws InvalidKeyException, NoSuchAlgorithmException {
        SecretKeySpec signingKey = new SecretKeySpec(key, ALGORITHM_HMAC_SHA1);
        Mac mac = Mac.getInstance(ALGORITHM_HMAC_SHA1);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data);
        return new String(Base64.encodeBase64(rawHmac));
    }


    public static boolean checkSign(HttpServletRequest  request, String clientId,
                                    String clientSecret ){

        String header = request.getHeader(HTTP_HEADER_AUTHORIZATION);
        String date = request.getHeader(HTTP_HEADER_DATE);
       /* Date sysdate= new Date(date);
        SimpleDateFormat df = new SimpleDateFormat(HTTP_HEADER_DATE_FORMAT, Locale.US);
        df.setTimeZone(TimeZone.getTimeZone(HTTP_HEADER_TIME_ZONE));
        String date = df.format(sysdate);*/
        String stringToSign = request.getMethod().toUpperCase() + " " +
                request.getRequestURI()+
                "\n" + date;
        String encoding;
        try {
            encoding = getSignature(stringToSign.getBytes(), clientSecret.getBytes());
        } catch (Exception e1) {
            logger.warn("Signature Exception occured:", e1);
            return false;
        }
        String sign = MEITUAN_AUTH_METHOD + " " + clientId + ":" + encoding;

        return sign.equals(header)? true:false;
    }

    public static void main(String[] args) {

    }
}

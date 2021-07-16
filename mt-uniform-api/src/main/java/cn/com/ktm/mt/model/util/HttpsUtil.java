package cn.com.ktm.mt.model.util;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Description:
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-07-05
 * @version V1.0
 * @Copyright: www.xxpay.org
 */
public class HttpsUtil {

    /**
     * 以https方式发送请求并将请求响应内容以String方式返回
     *
     * @param path   请求路径
     * @param method 请求方法
     * @param body   请求数据体
     * @return 请求响应内容转换成字符串信息
     */
    public static String httpsRequestToString(String path, String method, String body) {
        if (path == null || method == null) {
            return null;
        }
        String response = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        HttpsURLConnection conn = null;
        try {
            // 创建SSLConrext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new JEEWeiXinX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());

            // 从上述对象中的到SSLSocketFactory
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            System.out.println(path);

            URL url = new URL(path);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);

            //设置请求方式（git|post）
            conn.setRequestMethod(method);

            //有数据提交时
            if (null != body) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(body.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            inputStream = conn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            response = buffer.toString();
        } catch (Exception e) {

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            try {
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
            } catch (IOException execption) {

            }
        }
        return response;
    }
    /**
     * 通过HttpURLConnection模拟post表单提交
     *
     * @param path
     * @param params 例如"name=zhangsan&age=21"
     * @return
     * @throws Exception
     */
    public static InputStream sendPostRequestByForm(String path, String params) throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");// 提交模式
        conn.setConnectTimeout(10000);//连接超时 单位毫秒
        conn.setReadTimeout(2000);//读取超时 单位毫秒
        conn.setDoOutput(true);// 是否输入参数
        byte[] bypes = params.toString().getBytes();
        conn.getOutputStream().write(bypes);// 输入参数
        InputStream inStream=conn.getInputStream();
        return inStream;
    }
    /*
     * Function : 处理服务器的响应结果（将输入流转化成字符串） Param : inputStream服务器的响应输入流
     */
    private static String dealResponseResult(InputStream inputStream) {
        String resultData = null; // 存储处理结果
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        return resultData;
    }
    /**
     * get方法提交
     *
     * @param url
     *            String 访问的URL
     * @param param
     *            String 提交的内容
     * @param repType
     *            返回类型
     * @return String
     * */
    public static byte[] getServiceImg(String url, String params, String repType) {
        String result = "";
        byte[] resByt = null;
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj
                    .openConnection();

            // 连接超时
            conn.setDoInput(true);
            conn.setDoOutput(true);
            byte[] bypes = params.toString().getBytes();
            conn.getOutputStream().write(bypes);// 输入参数
            conn.setConnectTimeout(25000);

            // 读取超时 --服务器响应比较慢,增大时间
            conn.setReadTimeout(25000);
            conn.setRequestMethod("POST");

            conn.addRequestProperty("Accept-Language", "zh-cn");
            conn.addRequestProperty("Content-type", repType);
            conn.addRequestProperty(
                    "User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 2.0.50727)");
            conn.connect();

            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    conn.getOutputStream(), "UTF-8"), true);

            if ("image/jpeg".equals(repType)) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                BufferedImage bufImg = ImageIO.read(conn.getInputStream());
                ImageIO.write(bufImg, "jpg", outputStream);
                resByt = outputStream.toByteArray();
                outputStream.close();

            } else {
                // 取得输入流，并使用Reader读取
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

                System.out.println("=============================");
                System.out.println("Contents of get request");
                System.out.println("=============================");
                String lines = null;
                while ((lines = reader.readLine()) != null) {
                    System.out.println(lines);
                    result += lines;
                    result += "\r";
                }
                resByt = result.getBytes();
                reader.close();
            }
            out.print(resByt);
            out.flush();
            out.close();
            // 断开连接
            conn.disconnect();
            System.out.println("=============================");
            System.out.println("Contents of get request ends");
            System.out.println("=============================");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resByt;
    }
}

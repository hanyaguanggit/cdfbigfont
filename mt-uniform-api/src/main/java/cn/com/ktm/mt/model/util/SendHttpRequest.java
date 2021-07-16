package cn.com.ktm.mt.model.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: 高鹏
 * @date: 2019/10/12/012
 */
public class SendHttpRequest {

    /*
     * @Title: sendGetRequest
     * @Description: 发送get请求
     * @param:
     * @return:
     * @throws
     */
    public static String sendGetRequest(String url, String param) throws Exception {
        String result = "";
        BufferedReader in = null;
        try {
            // 拼接URL和参数
            String urlAndParam = url + "?" + param;
            // 创建URL对象
            URL realUrl = new URL(urlAndParam);
            // 打开URL的链接
            URLConnection conn = realUrl.openConnection();
            // 设置Head信息
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            // 建立链接
            conn.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            // 临时参数
            String line;
            // 获取响应结果
            while ((line = in.readLine()) != null) {
                result += line;
            }
        }catch(Exception e) {
            throw new Exception("请求失败");
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                throw new Exception("请求失败");
            }
        }
        return result;
    }


    /*
     * @Title:
     * @Description: 发送post请求
     * @param:
     * @return:
     * @throws
     */
    public static String sendPostRequest(String url, String param) throws Exception {
        String result = "";
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            // 创建URL对象
            URL realUrl = new URL(url);
            // 打开URL的链接
            URLConnection conn = realUrl.openConnection();
            // 设置Head信息
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            // POST请求设置
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 获取输入流,读取响应请求
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            // 临时参数
            String line;
            // 获取响应结果
            while ((line = in.readLine()) != null) {
                result += line;
            }
        }catch(Exception e) {
            throw new Exception("请求失败");
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                throw new Exception("请求失败");
            }
        }
        return result;
    }

}

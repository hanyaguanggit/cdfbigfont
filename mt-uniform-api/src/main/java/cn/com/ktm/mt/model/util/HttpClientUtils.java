package cn.com.ktm.mt.model.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMethod;

import javafx.util.Pair;


/**
 * HttpClient工具类
 */
@Slf4j
public class HttpClientUtils {

	public static final String URL_PARAM_CHARSET_UTF8 = "UTF-8"; // 定义编码格式 UTF-8
	public static final String URL_PARAM_CHARSET_GBK = "GBK"; // 定义编码格式 GBK
	private static final String EMPTY = "";

	private static MultiThreadedHttpConnectionManager connectionManager = null;
	private static int connectionTimeOut = 2000;
	private static int socketTimeOut = 4000;
	private static int maxConnectionPerHost = 20;
	private static int maxTotalConnections = 100;
	private static HttpClient client;

	static {
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
		connectionManager.getParams().setSoTimeout(socketTimeOut);
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
		connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
		client = new HttpClient(connectionManager);
	}



	/**
	 * POST方式提交数据
	 *
	 * @param url
	 *            待请求的URL
	 * @param params
	 *            要提交的数据
	 *            
	 * @param filedata
	 * 			文件数据
	 * @param charset
	 *            编码
	 * @return 响应结果
	 * @throws IOException
	 *             IO异常
	 */
	public static String sendPost(String url, Map<String, String> params,HTTPFile filedata ,String charset) throws Exception {
		url = url.replace("https", "http");
		org.apache.http.client.HttpClient httpclient = HttpClients.createDefault(); 
		String result="";
		HttpPost postMethod=new HttpPost(url);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE); 
		builder.setCharset(Charset.forName("UTF-8")); 
		// 将表单的值放入postMethod中
		if(params!=null){
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				NameValuePair p =new NameValuePair(key, value);
				builder.addTextBody(p.getName(), p.getValue(), ContentType.TEXT_PLAIN.withCharset("UTF-8"));
//				StringBody comment = new StringBody(value, ContentType.TEXT_PLAIN);
//				builder.addPart(key, comment);
//				ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
//				StringBody stringBody = new StringBody(value,contentType);
//				builder.addPart(key, stringBody);
				//builder.addTextBody(key, value, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			}
			
		}
		//for (HTTPFile file : filedata) {
			builder.addBinaryBody(filedata.getInputname(), filedata.getFiledata(), ContentType.MULTIPART_FORM_DATA, filedata.getFilename());
		//}
		try {
			 HttpEntity entity = builder.build();
			 postMethod.setEntity(entity);
			// 执行postMethod
			HttpResponse response = httpclient.execute(postMethod);
			int statusCode = response.getStatusLine().getStatusCode();  
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity responseEntity  = response.getEntity();
				if (responseEntity != null) {
	                // 将响应内容转换为字符串
	                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
	            }
			} else {
				throw new RuntimeException("响应状态码 = " +statusCode);
			}
		} catch (HttpException e) {
			throw new HttpException("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
		} catch (IOException e) {
			throw new IOException("发生网络异常", e);
		} catch (Exception e) {
			throw new Exception(String.format("POST方式提交数据异常（%s）", e.getMessage()), e);
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		return result;
	}
	
	public static String sendGetFile(String url, String fileName,String charset) throws Exception {
		url = url.replace("https", "http");
		String result="";
		GetMethod getMethod=new GetMethod(url);
		getMethod.setRequestHeader("Content-Type", "application/octet-stream;charset=" + charset);
		FileOutputStream output = null;
		try {
			int statusCode = client.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				File storeFile = new File(fileName);
				output = new FileOutputStream(storeFile);
				output.write(getMethod.getResponseBody());
				result = getMethod.getResponseBodyAsString();
			} else {
				throw new RuntimeException("响应状态码 = " +statusCode);
			}
		} catch (HttpException e) {
			throw new HttpException("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
		} catch (IOException e) {
			throw new IOException("发生网络异常", e);
		} catch (Exception e) {
			throw new Exception(String.format("POST方式提交数据异常（%s）", e.getMessage()), e);
		} finally {
			if (getMethod != null) {
				getMethod.releaseConnection();
			}
			try {
				if(output != null){
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * POST方式提交数据
	 *
	 * @param url
	 *            待请求的URL
	 * @param params
	 *            要提交的数据
	 * @param charset
	 *            编码
	 * @return 响应结果
	 * @throws IOException
	 *             IO异常
	 */
	public static byte[] sendPostByte(String url, Map<String, String> params, String charset) throws Exception {
		url = url.replace("https", "http");

		byte[] response = null;
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
		// 将表单的值放入postMethod中
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			String value = params.get(key);
			postMethod.addParameter(key, value);
		}
		try {
			// 执行postMethod
			int statusCode = client.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				response = postMethod.getResponseBody();
			} else {
				throw new RuntimeException("响应状态码 = " + postMethod.getStatusCode());
			}
		} catch (HttpException e) {
			throw new HttpException("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
		} catch (IOException e) {
			throw new IOException("发生网络异常", e);
		} catch (Exception e) {
			throw new Exception(String.format("POST方式提交数据异常（%s）", e.getMessage()), e);
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		return response;
	}
	/**
	 * 字节转FileInputStream
	 *
	 * @param bytes
	 * @return
	 */
	public static FileInputStream byteToFile(byte[] bytes, String fileName) {
		File file = new File(fileName);
		FileInputStream fileInputStream = null;
		try {
			OutputStream output = new FileOutputStream(file);
			BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
			bufferedOutput.write(bytes);
			fileInputStream = new FileInputStream(file);
			file.deleteOnExit();
			return fileInputStream;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileInputStream;
	}
	public static String sendPost(String url, Map<String, String> params, String charset) throws Exception {
		url = url.replace("https", "http");
		String response = EMPTY;
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
		// 将表单的值放入postMethod中
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			String value = params.get(key);
			postMethod.addParameter(key, value);
		}
		try {
			// 执行postMethod
			int statusCode = client.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
			} else {
				throw new RuntimeException("响应状态码 = " + postMethod.getStatusCode());
			}
		} catch (HttpException e) {
			throw new HttpException("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
		} catch (IOException e) {
			throw new IOException("发生网络异常", e);
		} catch (Exception e) {
			throw new Exception(String.format("POST方式提交数据异常（%s）", e.getMessage()), e);
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		return response;
	}

	/**
	 * POST方式提交数据
	 *
	 * @param url
	 *            待请求的URL
	 * @param params
	 *            要提交的数据
	 * @param charset
	 *            编码
	 * @return 响应结果
	 * @throws IOException
	 *             IO异常
	 */
	public static int sendPostGetStatus(String url, Map<String, String> params, String charset) throws Exception {
		url = url.replace("https", "http");
		int statusCode = 300;
		String response = EMPTY;
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
//		postMethod.setFollowRedirects(true);
		// 将表单的值放入postMethod中
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			String value = params.get(key);
			if(value == null){
				postMethod.addParameter(key, "");
			}else{
				postMethod.addParameter(key, value);
			}
		}
		try {
			// 执行postMethod
			statusCode = client.executeMethod(postMethod);
			return statusCode;

		} catch (HttpException e) {
			throw new HttpException("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
		} catch (IOException e) {
			throw new IOException("发生网络异常", e);
		} catch (Exception e) {
			throw new Exception(String.format("POST方式提交数据异常（%s）", e.getMessage()), e);
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		//		return statusCode;
	}

	public static String sendGet(String url, String charset) throws Exception {

		String response = EMPTY;
		GetMethod getMethod = new GetMethod(url);
		getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
		try {
			int statusCode = client.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				response = getMethod.getResponseBodyAsString();
			} else {
				throw new RuntimeException("响应状态码 = " + getMethod.getStatusCode());
			}
		} catch (HttpException e) {
			throw new HttpException("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
		} catch (IOException e) {
			throw new IOException("发生网络异常", e);
		} catch (Exception e) {
			throw new Exception(String.format("GET方式提交数据异常（%s）", e.getMessage()), e);
		} finally {
			if (getMethod != null) {
				getMethod.releaseConnection();
			}
		}
		return response;
	}

	/**
	 * POST方式提交数据
	 *
	 * @param url
	 *            待请求的URL
	 * @param body
	 *            要提交的数据
	 * @param charset
	 *            编码
	 * @return 响应结果
	 * @throws IOException
	 *             IO异常
	 */
	public static String sendPost2Body(String url, String body, String charset) throws Exception {
		url = url.replace("https", "http");

		StringBuffer response = new StringBuffer(EMPTY);
		InputStream inputStream = null;
		BufferedReader reader = null;
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
		// 将要提交的数据放入postMethod中
		postMethod.setRequestBody(body);
		try {
			// 执行postMethod
			int statusCode = client.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				inputStream = postMethod.getResponseBodyAsStream();
				reader = new BufferedReader(new InputStreamReader(inputStream, postMethod.getResponseCharSet()));
				String inputLine = null;
				while ((inputLine = reader.readLine()) != null) {
					response.append(inputLine);
				}
			} else {
				throw new Exception("响应状态码 = " + postMethod.getStatusCode());
			}
		} catch (HttpException e) {
			throw new HttpException("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
		} catch (IOException e) {
			throw new IOException("发生网络异常", e);
		} catch (Exception e) {
			throw new Exception(String.format("POST方式提交数据异常（%s）", e.getMessage()), e);
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		return response.toString();
	}

	/**
	 * POST方式提交数据
	 *
	 * @param url
	 *            待请求的URL
	 * @param body
	 *            要提交的数据
	 * @param charset
	 *            编码
	 * @param contentType
	 *            MIME类型
	 * @return 响应结果
	 * @throws IOException
	 *             IO异常
	 */
	public static String sendPost2Body(String url, String body, String charset, String contentType,String clientId,String clientSecret,String partnerId) throws Exception {
		StringBuffer response = new StringBuffer(EMPTY);
		InputStream inputStream = null;
		BufferedReader reader = null;
		PostMethod postMethod = new PostMethod(url);
		Pair<String, String>  pair = BasicAuthorizationUtils.createSign(RequestMethod.POST.name(), postMethod.getURI().getPath(), clientId, clientSecret);
		postMethod.setRequestHeader("Content-Type", contentType + ";charset=" + charset);
		postMethod.setRequestHeader(BasicAuthorizationUtils.HTTP_HEADER_PARTNERID, partnerId); // 请将partnerId替换为商家自身的partnerId
		postMethod.setRequestHeader(BasicAuthorizationUtils.HTTP_HEADER_AUTHORIZATION, pair.getValue());
		postMethod.setRequestHeader(BasicAuthorizationUtils.HTTP_HEADER_DATE, pair.getKey());
		// 将要提交的数据放入postMethod中
		postMethod.setRequestBody(body);
		for(int i=0;i<postMethod.getRequestHeaders().length;i++){
			log.info("sendPost2Bodyheadername="+postMethod.getRequestHeaders()[i].getName()+"value="+postMethod.getRequestHeaders()[i].getValue());
		}
		log.info("sendPost2Bodybody=========="+body);
		try {
			// 执行postMethod
			int statusCode = client.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				inputStream = postMethod.getResponseBodyAsStream();
				reader = new BufferedReader(new InputStreamReader(inputStream, postMethod.getResponseCharSet()));
				String inputLine = null;
				while ((inputLine = reader.readLine()) != null) {
					response.append(inputLine);
				}
			} else {
				throw new Exception("响应状态码 = " + postMethod.getStatusCode());
			}
		} catch (HttpException e) {
			throw new HttpException("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
		} catch (IOException e) {
			throw new IOException("发生网络异常", e);
		} catch (Exception e) {
			throw new Exception(String.format("POST方式提交数据异常（%s）", e.getMessage()), e);
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		return response.toString();
	}

	/**
	 * 通过HttpURLConnection模拟Json提交
	 *
	 * @param path
	 * @param param 例如"name=zhangsan&age=21"
	 * @return
	 * @throws Exception
	 */
	public static String sendPostRequestByJson(String path, String param) throws Exception{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(10000);//连接超时 单位毫秒
		conn.setReadTimeout(2000);//读取超时 单位毫秒
		conn.setDoOutput(true);// 是否输入参数
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");// 提交模式
		conn.setRequestProperty("Content-Type", "application/json");
		conn.connect();
		OutputStream os = conn.getOutputStream();
		PrintWriter out = new PrintWriter(os);
		out.print(param);
		out.flush();
		out.close();
		os.close();
		if (conn.getResponseCode() != 200 && conn.getErrorStream() != null) {
			return null;
		}
		conn.getContentLength();
		conn.getContentType();
		InputStream inStream=conn.getInputStream();
		return dealResponseResult(inStream);
	}
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




	public static String sendPost(String url, String body, String charset, String contentType ) throws Exception {
		url = url.replace("https", "http");

		StringBuffer response = new StringBuffer(EMPTY);
		InputStream inputStream = null;
		BufferedReader reader = null;
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("Content-Type", contentType + ";charset=" + charset);
		// 将要提交的数据放入postMethod中
		postMethod.setRequestBody(body);
		try {
			// 执行postMethod
			int statusCode = client.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				inputStream = postMethod.getResponseBodyAsStream();
				reader = new BufferedReader(new InputStreamReader(inputStream, postMethod.getResponseCharSet()));
				String inputLine = null;
				while ((inputLine = reader.readLine()) != null) {
					response.append(inputLine);
				}
			} else {
				throw new Exception("响应状态码 = " + postMethod.getStatusCode());
			}
		} catch (HttpException e) {
			throw new HttpException("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
		} catch (IOException e) {
			throw new IOException("发生网络异常", e);
		} catch (Exception e) {
			throw new Exception(String.format("POST方式提交数据异常（%s）", e.getMessage()), e);
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		return response.toString();
	}

	public static String sendPost(String url, Map<String, String> params, HTTPFile[] httpFiles, String charset) throws Exception {
		url = url.replace("https", "http");
		org.apache.http.client.HttpClient httpclient = HttpClients.createDefault();
		String result="";
		HttpPost postMethod=new HttpPost(url);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.setCharset(Charset.forName("UTF-8"));
		// 将表单的值放入postMethod中
		if(params!=null){
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				NameValuePair p =new NameValuePair(key, value);
				builder.addTextBody(p.getName(), p.getValue(), ContentType.TEXT_PLAIN.withCharset("UTF-8"));

			}

		}
		for(int i=0;i<httpFiles.length;i++){
			HTTPFile fileuploaddata = httpFiles[i];
			builder.addBinaryBody(fileuploaddata.getInputname(), fileuploaddata.getFiledata(), ContentType.MULTIPART_FORM_DATA, fileuploaddata.getFilename());
		}

		try {
			HttpEntity entity = builder.build();
			postMethod.setEntity(entity);
			// 执行postMethod
			HttpResponse response = httpclient.execute(postMethod);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity responseEntity  = response.getEntity();
				if (responseEntity != null) {
					// 将响应内容转换为字符串
					result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
				}
			} else {
				throw new RuntimeException("响应状态码 = " +statusCode);
			}
		} catch (HttpException e) {
			throw new HttpException("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
		} catch (IOException e) {
			throw new IOException("发生网络异常", e);
		} catch (Exception e) {
			throw new Exception(String.format("POST方式提交数据异常（%s）", e.getMessage()), e);
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		return result;

	}
}

package com.zhichenhaixin.chentong.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;


/**
 * 发送https请求的工具类
 * @version V1.0
 * @author pwl
 * @date 2018年6月9日 下午3:01:11
 * @Description 
 */
public class HttpsUtils {
	
		
	 public static String get(String url,String Cookie) {		   
		    HttpClient httpsClient = null;		    
	        HttpResponse response = null;
	        BufferedReader in = null;
	        String result = "";
	        try {
	        	httpsClient = new SSLClient();
	        	
	            HttpGet httpGet = new HttpGet(url);
	            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
	            httpGet.setConfig(requestConfig);
	            httpGet.setConfig(requestConfig);
	            httpGet.addHeader("Content-type", "application/json; charset=utf-8");
	            httpGet.setHeader("Accept", "application/json");  
	            if(Cookie!=null){
	             httpGet.addHeader("Cookie", Cookie);
	            }
	            response = httpsClient.execute(httpGet);
	            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String line = "";
	            String NL = System.getProperty("line.separator");
	            while ((line = in.readLine()) != null) {
	                sb.append(line + NL);
	            }
	            in.close();
	            result = sb.toString();
	        }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        return result;
	    }
	    
	    public static JSONObject getJson(String url,String Cookie) {
		    HttpClient httpsClient = null;	    	
	        HttpResponse response = null;
	        BufferedReader in = null;
	        JSONObject result = null;
	        try {
	        	httpsClient = new SSLClient();
	        	
	            HttpGet httpGet = new HttpGet(url);
	            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
	            httpGet.setConfig(requestConfig);
	            httpGet.setConfig(requestConfig);
	            httpGet.addHeader("Content-type", "application/json; charset=utf-8");
	            httpGet.setHeader("Accept", "application/json");  
	            if(Cookie!=null){
	             httpGet.addHeader("Cookie", Cookie);
	            }
	            response = httpsClient.execute(httpGet);
	                       
	            
	            result = JSONObject.parseObject(EntityUtils.toString(response.getEntity(), HTTP.UTF_8));
	        }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return result;
	    }
	    
	    public static String post(String url, String jsonString,String Cookie) {	    	
	    	HttpClient httpsClient = null;	    	
	        HttpResponse response = null;
	        BufferedReader in = null;
	        String result = "";
	        try {
	        	httpsClient = new SSLClient();
	        	
	            HttpPost httpPost = new HttpPost(url);
	            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
	            httpPost.setConfig(requestConfig);
	            httpPost.setConfig(requestConfig);
	            httpPost.addHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
	            httpPost.setHeader("Accept", "application/json");
	            if(Cookie!=null){
	            	httpPost.addHeader("Cookie", Cookie);
	            }
	            httpPost.setEntity(new StringEntity(jsonString, Charset.forName("UTF-8")));
	            response = httpsClient.execute(httpPost);
	            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String line = "";
	            String NL = System.getProperty("line.separator");
	            while ((line = in.readLine()) != null) {
	                sb.append(line + NL);
	            }
	            in.close();
	            result = sb.toString();
	        }catch (Exception e) {				
				e.printStackTrace();
			} 
	        
	        return result;
	    }
		
}

package com.zhichenhaixin.chentong.utils;

//import com.zhichenhaixin.lap.back.utils.ResourceUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Title: GateWay.java
 * @Package com.zhichenhaixin.lap.back.gateway
 * @Description: TODO( 网关：用于不同服务间数据的交互，包括数据的获取与传递)
 * @author ZFM
 * @date 2016年10月20日 上午10:20:54
 * @version V1.0
 */
public class GateWay {

	//private static final String targetURL = "http://" + ResourceUtil.getNetIp() + ":" + ResourceUtil.getNetPort() + "/net-manager/back/";
	/**
	 * 网关调用其它服务restful的get方法
	 * 
	 * @param uri
	 *            格式如ship/ships
	 * @return
	 */
	public static String getRESTFulClient(String uri) throws Exception{  //zbh: 抛出异常,让调用它的方法获取异常进行catch
		String returnStr = "";// 返回字符串
		StringBuffer buffer = new StringBuffer();
		//try {
			URL restServiceURL = new URL(uri);
			System.out.println("请求地址：" + restServiceURL);
			HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Accept", "application/json");
			httpConnection.setConnectTimeout(2000); //设置连接主机超时时间 ,否则时间太长
			if (httpConnection.getResponseCode() != 200) {
				throw new RuntimeException(
						"HTTP GET Request Failed with Error code : " + httpConnection.getResponseCode());
			}

			BufferedReader responseBuffer = new BufferedReader(
					new InputStreamReader((httpConnection.getInputStream())));

			System.out.println("Output from Server:");

			while ((returnStr = responseBuffer.readLine()) != null) {
				System.out.println(returnStr);
				buffer.append(returnStr);
			}

			httpConnection.disconnect();

		//} catch (IOException e) {

			//e.printStackTrace();
		//	System.out.println("EEEE");
		//}
		return buffer.toString();

	}

	/**
	 * 网关调用其它服务restful的post方法
	 * 
	 * @param uri
	 *            格式如：ship/ships
	 * @param param
	 *            json格式,对应请求的参数
	 * @return
	 */
	public static String postRESTFulClient(String uri, String param) throws Exception {
		String returnStr = "";// 返回字符串
		StringBuffer buffer = new StringBuffer();
		//try {
			URL targetUrl = new URL(uri);
			System.out.println("请求地址：" + targetUrl);
			HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", "application/json");
			httpConnection.setConnectTimeout(1000); //设置连接主机超时时间 ,否则时间太长
			// input =
			// "{\"id\":1,\"firstName\":\"Liam\",\"age\":22,\"lastName\":\"Marco\"}";
			OutputStream outputStream = httpConnection.getOutputStream();
			outputStream.write(param.getBytes("utf-8"));
			outputStream.flush();

			if (httpConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + httpConnection.getResponseCode());
			}

			BufferedReader responseBuffer = new BufferedReader(
					new InputStreamReader((httpConnection.getInputStream())));

			System.out.println("Output from Server:");
			while ((returnStr = responseBuffer.readLine()) != null) {
				System.out.println(returnStr);
				buffer.append(returnStr);
			}
			httpConnection.disconnect();
 

	 	/*} catch (IOException e) {
	 		 
			e.printStackTrace();

		}*/
		return buffer.toString();

	}

	public static void main(String[] args) {
		// getRESTFulClient();
		// postRESTFulClient("test");
	}
}

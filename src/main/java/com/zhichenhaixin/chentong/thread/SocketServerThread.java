package com.zhichenhaixin.chentong.thread;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhichenhaixin.chentong.ehcache.AdhocEhcache;
import com.zhichenhaixin.chentong.ehcache.AisRealDataEhcache;
import com.zhichenhaixin.chentong.model.adhoc.AdhocInfo;
import com.zhichenhaixin.chentong.model.aerials.Aerials;
import com.zhichenhaixin.chentong.model.ais.AisRealData;
import com.zhichenhaixin.chentong.model.ships.ShipInfo;
import com.zhichenhaixin.chentong.utils.BaseUtil;
import com.zhichenhaixin.chentong.utils.ResourceUtil;
import com.zhichenhaixin.chentong.utils.ResultMsgUtil;

/**
 * socket服务端线程类：监听并接收客户端推送的数据
 *
 * @version V1.0
 * @author pwl
 * @date 2019年5月11日9:13:3
 * @Description 
 *
 */
@Component
public class SocketServerThread extends Thread {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SocketServerThread.class);
	//建立socket服务
	private static ServerSocket serverSocket = null;
	
	private static Socket socket = null; 
	
	private static DataOutputStream outputStream = null;
	
	private static DataInputStream inputStream = null;

	public SocketServerThread() {
	}
	
	@Override
	public void run() {
		/**
		 * 初始化一个socket服务端
		 */
		try {
			serverSocket = new ServerSocket(Integer.parseInt(ResourceUtil.socketServerPort));
		} catch (IOException e) {
			LOGGER.info("socket服务端线程创建失败，" + e.getMessage());
		}
		/**
		 * 一直循环监听客户端请求
		 */
		while(true){
			LOGGER.info("socket服务端线程启动...");
			//LOGGER.info("socket服务端线程启动..." + clientThread);
			
			try {
				socket = serverSocket.accept();
				LOGGER.info("有新连接>>>>>>：" + socket.getInetAddress() + ":" + socket.getPort());
				//接收客户端推送过来的数据
				readData(socket);
				Thread.sleep(2*1000);
			} catch (Exception e) {
				//e.getStackTrace();
				LOGGER.info("SocketServerThread-出错了，" + e.getMessage());
			}
			
		}
	}
	/**
	 * 读取数据并处理
	 * @param socket
	 */
	private void readData(Socket socket){
		try {
			if(inputStream == null) {
				inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			}
			byte[] message = readMessage();
			if(null != message){
				String msg = new String(message, "UTF-8");
				String[] json = msg.split("AAAAA");//拆包
				JSONObject result = ResultMsgUtil.success();
				LOGGER.info("socket服务端接收到的数据是：" + msg);
				
				for (int q = 0; q < json.length; q++) {
					
					JSONObject obj = JSONObject.parseObject(json[q]); 
					String code = obj.get("code").toString();
					String msgtype = obj.get("msgtype").toString();
					Map aerialsMap = new HashedMap();
					
					//int rows = Integer.parseInt(obj.get("rows").toString());
					if(!StringUtils.isBlank(msgtype) && !StringUtils.isBlank(code) && "1".equals(code)){
						JSONArray data = obj.getJSONArray("data");
						//接收组网信息
						if(ResourceUtil.msgType.equals(msgtype)){
							AisRealData ais = new AisRealData();
							for (int i = 0; i < data.size(); i++) {
								
								HashMap<String,ShipInfo> shipMap = new HashMap<>();
								
								JSONObject o = data.getJSONObject(i);
								AdhocInfo adhoc = (AdhocInfo)JSONObject.toJavaObject(o, AdhocInfo.class);
								//取出所有天线的IP和编号
								for (String key : adhoc.getShip().keySet()) {
									ShipInfo ship = adhoc.getShip().get(key);
									List<Aerials> list = ship.getAerialsList();
									for (Aerials a : list) {
										if(!StringUtils.isBlank(a.getAerialsIp())){
											aerialsMap.put(a.getAerialsIp(), a.getAerialsCode());
										} 
									}
								}
								
								for (String key : adhoc.getShip().keySet()) {
									ShipInfo ship = adhoc.getShip().get(key);
									List<Aerials> list = ship.getAerialsList();
									//给关联天线编号赋值
									for (int j = 0; j < list.size(); j++) {
										String linkCode = BaseUtil.trim(aerialsMap.get(list.get(j).getLinkAerialsIp()));
										if(!StringUtils.isBlank(linkCode)){
											list.get(j).setLinkAerialsCode(linkCode);
										}
									}
									//更新AIS坐标
									ais = AisRealDataEhcache.get(ship.getShipMmsi());
									if(null != ais){
										ship.setAislat(ais.getLatitude());
										ship.setAislng(ais.getLongitude());
									}
									ship.setAerialsList(list);
									shipMap.put(ship.getShipCode(), ship);
								}
								adhoc.setShip(shipMap);
								//清空后再添加
								AdhocEhcache.clear();
								AdhocEhcache.put(adhoc);
							}
							LOGGER.info("当前缓存中的组网记录数：" + AdhocEhcache.getAll().size());
							//接收AIS信息
						}else if(ResourceUtil.aisMsgType.equals(msgtype)){
							//清空后再添加
							//AisRealDataEhcache.clear();
							for (int i = 0; i < data.size(); i++) {
								JSONObject o = data.getJSONObject(i);
								AisRealData ais = (AisRealData)JSONObject.toJavaObject(o, AisRealData.class);
								AisRealDataEhcache.put(ais);
							}
							LOGGER.info("当前缓存中的AIS记录数：" + AisRealDataEhcache.getAll().size());
						}
					}
				}
				if(outputStream == null) {
					outputStream = new DataOutputStream(socket.getOutputStream());
				}
				//发送返回信息
				sendMessage(result.toJSONString());
			}else{
				sendMessage(ResultMsgUtil.error("解析失败").toJSONString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("读取数据异常：" + e.getMessage());
		}finally{			
			closeConn();
		}
	}
	/**
	 * 关闭流和连接
	 */
	private void closeConn(){
		try {
			
			if(null != outputStream){
				outputStream.flush();
				outputStream.close();
				outputStream = null;
			}
			if(null != inputStream){
				inputStream.close();
				inputStream = null;
			}
			if(null != socket){
				socket.close();
				socket = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("关闭socket异常：" + e.getMessage());
		}
	}
	/**
	 * 解析信息
	 * @param inputStream
	 * @return
	 */
	private byte[] readMessage() {
        try {
            byte[] bytes = new byte[20000];
            int length = inputStream.read(bytes);
            byte[] blenth = new byte[length];
            System.arraycopy(bytes, 0, blenth, 0, length);
            return blenth;
        } catch (Exception e) {
            LOGGER.error("解析信息异常，{}",e.getMessage());
            return null;
        }
    } 
	/**
	 * 发送信息
	 * @param outputStream
	 */
	private void sendMessage(String json) {
        try {
        	outputStream.write(json.getBytes());
        	outputStream.flush();
        } catch (Exception e) {

            LOGGER.error("解析信息异常，{}",e.getMessage());
        }
    } 
}

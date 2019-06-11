package com.zhichenhaixin.chentong.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhichenhaixin.chentong.utils.ResourceUtil;
import com.zhichenhaixin.chentong.utils.ResultMsgUtil;

/**
 * 用于系统初始化的服务
 * 
 * @version V1.0
 * @author pwl
 * @date 2018年6月14日 下午4:00:28
 * @Description
 */
@Service("sysInitService")
public class SysInitService {

	@Value("${properties.sapId}")
	private String sapId;

	@Value("${properties.properties_path}")
	private String properties_path;

	@Value("${properties.download_path}")
	private String download_path;
	
	@Value("${user.loginName}")
	private String userName;
	
	@Value("${user.password}")
	private String userPassword;
	
	@Value("${client.msgtype}")
	private String msgType;
	
	@Value("${client.aisMsgType}")
	private String aisMsgType;
	
	@Value("${socket.server.port}")
	private String socketServerPort;

/*	@Value("${net.ip}")
	private String net_ip;

	@Value("${net.port}")
	private String net_port;

	@Value("${net.heart.port}")
	private String net_heart_port;*/



	/**
	 * 初始化配置参数的服务
	 * 
	 * @return
	 * @author zhangbohu
	 */
	public JSONObject initProperties() {

		return ResultMsgUtil.success("初始化参数成功!");
	}

	/**
	 * 初始化 initResourceUtil工具类
	 * 
	 * @author zhangbohu
	 */
	public JSONObject initResourceUtil() {

		ResourceUtil.sapId = sapId;

		ResourceUtil.properties_path = properties_path;

		ResourceUtil.download_path = download_path;
		
		ResourceUtil.userName = userName;
		
		ResourceUtil.userPassword = userPassword;
		
		ResourceUtil.msgType = msgType;
		
		ResourceUtil.aisMsgType = aisMsgType;
		
		ResourceUtil.socketServerPort = socketServerPort;
		

		/*ResourceUtil.netIp = net_ip;

		ResourceUtil.netPort = net_port;

		ResourceUtil.netHeartPort = net_heart_port;*/

		return ResultMsgUtil.success("初始化配置信息成功!");

	}

	

	public static void main(String[] args) {
		String str = "[{\"longitude\":111.00,\"latitude\":5.406},{\"longitude\":222.22,\"latitude\":41.406},{\"longitude\":333.55,\"latitude\":61.406},{\"longitude\":444.99,\"latitude\":11.406} ]";

		JSONArray jsonA = JSONObject.parseArray(str);
		System.out.println(jsonA.get(0).toString());
		System.out.println(jsonA.get(1).toString());
		System.out.println(jsonA.get(2).toString());
		System.out.println(jsonA.get(3).toString());

	}



	/**
	 * 开启多个线程
	 * 
	 * @author zhangbohu
	 */
	public void startThreads() {

		// 获取所有的转台
		//List<Turntable> turntableList = turntableService.getLocalTurntable(Integer.valueOf(ResourceUtil.sapId));
		
		/*for (Turntable turntable : turntableList) {
			// 开启本云台和云台设备通讯的socket线程
			TurntableServerSocket serverSocket = new TurntableServerSocket(turntable.getId(), turntable.getWebPort());

			serverSocket.start();
			// 放进集合中方便系统退出时候关闭
			SERVERTHREADMAP.put(turntable.getId(), serverSocket);
			// 为每个转台开启服务
			StationMateThread mateThread = new StationMateThread(turntable.getId());

			mateThread.start();
			// 放进集合中方便系统退出时候关闭
			MATETHREADMAP.put(turntable.getId(), mateThread);
		}*/
		// TODO Auto-generated method stub
		// HeartbeatTask hTask = new HeartbeatTask();
		// hTask.run();
		// HeartClient hc = new HeartClient();
		// hc.run();

		// HeartClient2 hc2 = new HeartClient2();
		// hc2.run();
		//StationStatusPush push = new StationStatusPush(turntableList.get(0).getId());
		//push.start();
		
		System.out.println("startThread_over");

	}

}

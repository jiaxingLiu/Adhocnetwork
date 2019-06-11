package com.zhichenhaixin.chentong.utils;
 
   
import lombok.Getter;
import lombok.Setter;

/**
 * 读取配置文件信息的类
 * 
 * @version V1.0
 * @author pwl
 * @date 2018年6月11日 上午11:24:38
 * @Description
 */
 
@Getter
@Setter
public class ResourceUtil {
 
	/** 当前船站id */
	public static String sapId = null; 
 
	/** 当前岸站id */
	public static String lapId = null;
	
	/** 基础信息配置文件路径 */
	public static String properties_path = null; 
	
	/** 下载配置文件存放的路径 */
	public static String download_path = null;
	
	public static String localportId = null;
	/**
	 * 登陆用户名
	 */
	public static String userName = null;
	/**
	 * 登陆用户名密码
	 */
	public static String userPassword = null;
	/**
	 * 推送组网信息过来的数据类型
	 */
	public static String msgType = null;
	/**
	 * 推送AIS实时数据过来的数据类型
	 */
	public static String aisMsgType = null;
	/**
	 * socket服务器端口号
	 */
	public static String socketServerPort = null;

	
	/**Net IP port**/
	/*public static String netIp =null;
	
	public static String netPort =null;
	
	public static String netHeartPort =null;*/
}

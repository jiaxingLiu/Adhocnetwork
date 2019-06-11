package com.zhichenhaixin.chentong.model.aerials;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 天线实体类
 * @version V1.0
 * @author pwl
 * @date 2019年5月8日10:15:54
 * @Description 
 */
@Getter
@Setter
public class Aerials implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 918526444441157509L;
	/**
	 * 天线编号
	 */
	private String aerialsCode;
	/**
	 * 天线类型:天线设备的型号
	 */
	private String aerialsType;
	/**
	 * 天线IP
	 */
	private String aerialsIp;
	/**
	 * 频点
	 */
	private String point;
	/**
	 * SSID
	 */
	private String ssid;
	/**
	 * 连接状态:1-连接中,0-断开
	 */
	private String status;
	
	/**
	 * 天线关联的船站编号
	 */
	private String linkShipCode;
	/**
	 * 天线关联的天线编号
	 */
	private String linkAerialsCode;
	/**
	 * 天线关联的天线IP
	 */
	private String linkAerialsIp;
	/**
	 * 天线关联的类型  0 是主连  1 是被连  2是未连
	 */
	private String linkType;
}

package com.zhichenhaixin.chentong.model.adhoc;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 天线连接情况
 * @author pwl
 *
 */
@Getter
@Setter
public class ShipLinkVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5353628227830069220L;
	/**
	 * 组网链路
	 */
	private String linkNum;
	/**
	 * 主动-船站名称
	 */
	private String shipInName;
	/**
	 * 主动-天线编号
	 */
	private String aerialsInCode;
	/**
	 * 主动-天线IP
	 */
	private String aerialsInIp;
	/**
	 * 被动-船站名称
	 */
	private String shipOutName;
	/**
	 * 被动-天线编号
	 */
	private String aerialsOutCode;
	/**
	 * 被动-天线IP
	 */
	private String aerialsOutIp;
	/**
	 * 当前时间
	 */
	private String currentTime;
	/**
	 * 天线频点
	 */
	private String point;
	/**
	 * SSID
	 */
	private String ssid;
	/**
	 * 连接状态
	 */
	private String status;
	
	
	
}

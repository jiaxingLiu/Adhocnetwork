package com.zhichenhaixin.chentong.model.adhoc;

import java.io.Serializable;
import java.util.HashMap;

import com.zhichenhaixin.chentong.model.ships.ShipInfo;

import lombok.Getter;
import lombok.Setter;
/**
 * 自组网实体类
 * @version V1.0
 * @author pwl
 * @date 2019年5月8日10:15:54
 * @Description 
 */
@Getter
@Setter
public class AdhocInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4187547089398894649L;
	/**
	 * 组网编号 
	 */
	private String adhocCode;
	/**
	 * 组网名称
	 */
	private String adhocName;
	/**
	 * 本船站编号
	 */
	private String shipCode;
	/**
	 * 本船站名称
	 */
	private String shipName;
	/**
	 * 天线关联的船编号-出
	 */
	//private String linkOutCode;
	/**
	 * 组网内的船站信息  key为船站编号
	 */
	private HashMap<String,ShipInfo> ship;
	
}

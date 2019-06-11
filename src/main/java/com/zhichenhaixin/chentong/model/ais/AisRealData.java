package com.zhichenhaixin.chentong.model.ais;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * AIS实时数据实体类
 * @author zchx
 *
 */
@Getter
@Setter
public class AisRealData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1330616441983892709L;
	/******船站mmsi******/
	private String mmsi;
	/******船站名称******/
	private String shipName;
	/******AIS经度******/
	private String longitude;
	/******AIS纬度******/
	private String latitude;
	/******船的航速******/
	private String sog;
	/******船首向******/
	private String heading;
}

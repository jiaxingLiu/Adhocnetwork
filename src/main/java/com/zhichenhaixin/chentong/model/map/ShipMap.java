package com.zhichenhaixin.chentong.model.map;

import java.io.Serializable;
/**
 * 船站信息实体类（地图用）
 * @version V1.0
 * @author pwl
 * @date 2019年5月11日9:13:3
 * @Description 
 */
public class ShipMap implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3932881549004429697L;

	/******船站编号******/
	private String sId;
	
	/******船站名称******/
	private String sName;
	
	/******组网状态 0-未组网,1-已组网******/
	private Integer sStatus;

	/******AIS经度******/
	private Double sLongitude = 0.0;
	
	/******AIS纬度******/
	private Double sLatitude = 0.0;
	
	/******被接入船站AIS经度******/
	private Double lLongitude = 0.0;
	
	/******被接入船站AIS纬度******/
	private Double lLatitude = 0.0;
	
	/******被接入船站GPS经度******/
	private Double lGpsLongitude = 0.0;
	
	/******被接入船站GPS纬度******/
	private Double lGpsLatitude = 0.0;
	
	/******GPS经度******/
	private Double sGpsLongitude = 0.0;
	
	/******GPS纬度******/
	private Double sGpsLatitude = 0.0;
	
	/******船的航速******/
	private Float sog = 0.0f;
	
	/******时间******/
	private Long utc = 0l;
	
	/********船首向*************/
	private Float heading = 0.0f;

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public Double getsLongitude() {
		return sLongitude;
	}

	public void setsLongitude(Double sLongitude) {
		this.sLongitude = sLongitude;
	}

	public Double getsLatitude() {
		return sLatitude;
	}

	public void setsLatitude(Double sLatitude) {
		this.sLatitude = sLatitude;
	}

	public Double getlLongitude() {
		return lLongitude;
	}

	public void setlLongitude(Double lLongitude) {
		this.lLongitude = lLongitude;
	}

	public Double getlLatitude() {
		return lLatitude;
	}

	public void setlLatitude(Double lLatitude) {
		this.lLatitude = lLatitude;
	}

	public Double getsGpsLongitude() {
		return sGpsLongitude;
	}

	public void setsGpsLongitude(Double sGpsLongitude) {
		this.sGpsLongitude = sGpsLongitude;
	}

	public Double getsGpsLatitude() {
		return sGpsLatitude;
	}

	public void setsGpsLatitude(Double sGpsLatitude) {
		this.sGpsLatitude = sGpsLatitude;
	}

	public Float getSog() {
		return sog;
	}

	public void setSog(Float sog) {
		this.sog = sog;
	}

	public Long getUtc() {
		return utc;
	}

	public void setUtc(Long utc) {
		this.utc = utc;
	}

	public Float getHeading() {
		return heading;
	}

	public void setHeading(Float heading) {
		this.heading = heading;
	}
	public Integer getsStatus() {
		return sStatus;
	}

	public void setsStatus(Integer sStatus) {
		this.sStatus = sStatus;
	}

	public Double getlGpsLongitude() {
		return lGpsLongitude;
	}

	public void setlGpsLongitude(Double lGpsLongitude) {
		this.lGpsLongitude = lGpsLongitude;
	}

	public Double getlGpsLatitude() {
		return lGpsLatitude;
	}

	public void setlGpsLatitude(Double lGpsLatitude) {
		this.lGpsLatitude = lGpsLatitude;
	}
	
}

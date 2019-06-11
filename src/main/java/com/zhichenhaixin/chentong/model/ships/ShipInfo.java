package com.zhichenhaixin.chentong.model.ships;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zhichenhaixin.chentong.model.aerials.Aerials;
import com.zhichenhaixin.chentong.utils.BaseUtil;
import com.zhichenhaixin.chentong.utils.DateFormatUtil;

import lombok.Getter;
import lombok.Setter;
/**
 * 船站实体类
 * @version V1.0
 * @author pwl
 * @date 2019年5月8日10:15:54
 * @Description 
 */
@Getter
@Setter
public class ShipInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4615588597225162105L;
	/**
	 * 船站编号
	 */
	private String shipCode;
	/**
	 * 船站名称
	 */
	private String shipName;
	/**
	 * 船首向
	 */
	private String shipDirection;
	/**
	 * 船站Mmsi
	 */
	private String shipMmsi;
	/**
	 * 船站IP
	 */
	private String shipIp;
	/**
	 * AIS经度
	 */
	private String aislng;
	/**
	 * AIS纬度
	 */
	private String aislat;

	/**
	 * GPS经度
	 */
	private String gpslng;
	
	/**
	 * GPS纬度
	 */
	private String gpslat;
	/**
	 * 船站类型: 0-本船 1-组网船 2-待组网船三种
	 */
	private String shipType;
	/**
	 * 船站状态:0-未组网,1-已组网
	 */
	private String shipStatus;
	/**
	 * 船站端口
	 */
	private String shipPort;
	/**
	 * 安装日期:毫秒数
	 */
	private String installDate;
	/**
	 * 安装高度
	 */
	private String installHeight;
	/**
	 * 天线list集合
	 */
	private List<Aerials> aerialsList;
	
	public void setAislng(String aislng){
		this.aislng = BaseUtil.trimDecimal(aislng, "0.00", 6);
	}
	public void setAislat(String aislat){
		this.aislat = BaseUtil.trimDecimal(aislat, "0.00", 6);
	}
	public void setGpslng(String gpslng){
		this.gpslng = BaseUtil.trimDecimal(gpslng, "0.00", 6);
	}
	public void setGpslat(String gpslat){
		this.gpslat = BaseUtil.trimDecimal(gpslat, "0.00", 6);
	}
	public void setInstallDate(String installDate) {
		//日期格式化
		if(!StringUtils.isBlank(installDate) && installDate.indexOf("-") < 0){ 
			this.installDate= DateFormatUtil.dateFormat(Long.parseLong(installDate), "yyyy-MM-dd HH:mm:ss");
		}else{
			this.installDate = installDate;
		}
	}
}

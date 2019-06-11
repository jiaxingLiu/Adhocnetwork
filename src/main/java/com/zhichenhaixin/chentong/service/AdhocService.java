package com.zhichenhaixin.chentong.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhichenhaixin.chentong.ehcache.AdhocEhcache;
import com.zhichenhaixin.chentong.ehcache.AisRealDataEhcache;
import com.zhichenhaixin.chentong.model.adhoc.AdhocInfo;
import com.zhichenhaixin.chentong.model.adhoc.ShipLinkVo;
import com.zhichenhaixin.chentong.model.aerials.Aerials;
import com.zhichenhaixin.chentong.model.ais.AisRealData;
import com.zhichenhaixin.chentong.model.map.ShipMap;
import com.zhichenhaixin.chentong.model.ships.ShipInfo;
import com.zhichenhaixin.chentong.utils.BaseUtil;
import com.zhichenhaixin.chentong.utils.DateFormatUtil;

/**
 * 自组网处理类<br>
 * 1.接收客户端推送的实时数据<br>
 * 2.保存数据到缓存中<br>
 * 3.获取缓存数据<br>
 * @version V1.0
 * @author pwl
 * @date 2019年5月11日9:13:3
 * @Description 
 *
 */
@Service("adhocService")
public class AdhocService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdhocService.class);

	/**
	 * 获取本船的所有信息
	 * @return
	 */
	public ShipInfo getOneselfShipInfo(){
		List<AdhocInfo> list = AdhocEhcache.getAll();
		for (AdhocInfo adhoc : list) {
			HashMap<String,ShipInfo> ships = adhoc.getShip();
			return ships.get(adhoc.getShipCode());
		}
		return null;
	}
	/**
	 * 根据组网编号获取组网信息
	 * @param adhocCode
	 * @return
	 */
	public AdhocInfo getAdhocByCode(String adhocCode){
		
		AdhocInfo adhoc = AdhocEhcache.get(adhocCode);
		
		return adhoc;
	}
	/**
	 * 获取组网天线连接情况
	 * @return
	 */
	public List<ShipLinkVo> getShipLinkVo(String adhocCode){
		AdhocInfo adhoc = getAdhocByCode(adhocCode);
		List<ShipLinkVo> list = new ArrayList<>();
		if(adhoc != null){
			String currentTime = DateFormatUtil.dateFormat(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
			ShipLinkVo vo = new ShipLinkVo();
			String shipCode = adhoc.getShipCode();
			ShipInfo ship = new ShipInfo();
			ship = adhoc.getShip().get(shipCode);
			
			/*************   本船     *******************/
			Aerials aerials = GetAerials(ship,"0");
			String linkShipCode = aerials.getLinkShipCode();
			//组网链路
			vo.setLinkNum("1");
			//主动-船站名称			
			vo.setShipInName(ship.getShipName());
			//主动-天线编号
			vo.setAerialsInCode(aerials.getAerialsCode());
			//主动-天线IP
			vo.setAerialsInIp(aerials.getAerialsIp());
			//被动-船站名称
			vo.setShipOutName(adhoc.getShip().get(linkShipCode).getShipName());
			//被动-天线编号
			vo.setAerialsOutCode(aerials.getLinkAerialsCode());
			//被动-天线IP
			vo.setAerialsOutIp(aerials.getLinkAerialsIp());
			//当前时间
			vo.setCurrentTime(currentTime);
			//天线频点
			vo.setPoint(aerials.getPoint());
			//SSID
			vo.setSsid(aerials.getSsid());
			//连接状态
			vo.setStatus(aerials.getStatus());
			
			list.add(vo);
			
			/*************   组网船     *******************/
			int num = 2 ;
			while (null != linkShipCode && linkShipCode.length() > 0) {
				ShipInfo s = adhoc.getShip().get(linkShipCode);
				Aerials a = GetAerials(s,"0");
				linkShipCode = a.getLinkShipCode();
				
				if(null == linkShipCode) break;
				
				ShipLinkVo v = new ShipLinkVo();
				//组网链路
				v.setLinkNum(num + "");
				//主动-船站名称			
				v.setShipInName(s.getShipName());
				//主动-天线编号
				v.setAerialsInCode(a.getAerialsCode());
				//主动-天线IP
				v.setAerialsInIp(a.getAerialsIp());
				//被动-船站名称
				v.setShipOutName(adhoc.getShip().get(linkShipCode).getShipName());
				//被动-天线编号
				v.setAerialsOutCode(a.getLinkAerialsCode());
				//被动-天线IP
				v.setAerialsOutIp(a.getLinkAerialsIp());
				//当前时间
				v.setCurrentTime(currentTime);
				//天线频点
				v.setPoint(a.getPoint());
				//SSID
				v.setSsid(a.getSsid());
				//连接状态
				v.setStatus(a.getStatus());
				
				list.add(v);
				num ++;
			}
		}
		
		return list;
	}
	/**
	 * 
	 * @param ship
	 * @param linkType 0 是主连  1 是被连  2是未连
	 * @return
	 */
	public Aerials GetAerials(ShipInfo ship,String linkType){
		Aerials aerials = new Aerials();
		for (Aerials a : ship.getAerialsList()) {
			if(a.getLinkType().equals(linkType)){
				return a;
			}
		}
		return aerials;
	}
	/**
	 * 获取所有组网信息
	 * @return
	 */
	public List<AdhocInfo> getAllAdhoc(){
		
		List<AdhocInfo> adhocList = AdhocEhcache.getAll();
		
		return adhocList;
	}
	/**
	 * 获取所有组网下的所有船站信息
	 * @return
	 */
	public List<ShipInfo> getAllShipInfo(){
		
		List<ShipInfo> list = new ArrayList<>();
		List<AdhocInfo> adhocList = AdhocEhcache.getAll();
		Set set = new HashSet<>();
		for (AdhocInfo adhoc : adhocList) {
			HashMap<String,ShipInfo> ships = adhoc.getShip();
			for (String key : ships.keySet()) {
				set.add(ships.get(key));
			}
		}
		list.addAll(set);
		return list;
	}
	/**
	 * 查询组网下的所有船站信息，组装成map用的对象
	 * @return
	 */
	public List<ShipMap> getShipMap(){
		
		List<AdhocInfo> adhocList = AdhocEhcache.getAll();
		
		List<ShipMap> list = new ArrayList<>();
		
		for (AdhocInfo adhoc : adhocList) {
			
			String shipCode = adhoc.getShipCode();
			ShipInfo ship = new ShipInfo();
			ship = adhoc.getShip().get(shipCode);
			if (ship == null) break;
			Aerials aerials = GetAerials(ship,"0");
			String linkShipCode = aerials.getLinkShipCode();
			
			/*************   本船     *******************/
			if(!((StringUtils.isBlank(ship.getAislat()) || StringUtils.isBlank(ship.getAislng())) 
					&& (StringUtils.isBlank(ship.getGpslat()) || StringUtils.isBlank(ship.getGpslng())))){
					ShipMap map = new ShipMap();
					map.setsId(ship.getShipCode());
					map.setsName(ship.getShipName());
					map.setsStatus(Integer.parseInt(ship.getShipStatus()));
					map.setHeading(BaseUtil.getFloat(ship.getShipDirection(), 0.0f));
					map.setsLatitude(BaseUtil.getDouble(ship.getAislat(), 0.0));
					map.setsLongitude(BaseUtil.getDouble(ship.getAislng(), 0.0));
					map.setsGpsLatitude(BaseUtil.getDouble(ship.getGpslat(), 0.0));
					map.setsGpsLongitude(BaseUtil.getDouble(ship.getGpslng(), 0.0));
					map.setUtc(System.currentTimeMillis());
					ShipInfo linkship = adhoc.getShip().get(linkShipCode);
					if(null != linkship){
						map.setlLatitude(BaseUtil.getDouble(linkship.getAislat(), 0.0));
						map.setlLongitude(BaseUtil.getDouble(linkship.getAislng(), 0.0));
						map.setlLatitude(BaseUtil.getDouble(linkship.getGpslat(), 0.0));
						map.setlLongitude(BaseUtil.getDouble(linkship.getGpslng(), 0.0));
					}
					
					list.add(map);
			}

			/*************   组网船     *******************/
			while (null != linkShipCode && linkShipCode.length() > 0) {
				ShipInfo s = adhoc.getShip().get(linkShipCode);
				Aerials a = GetAerials(s,"0");
				linkShipCode = a.getLinkShipCode();
				if(!((StringUtils.isBlank(s.getAislat()) || StringUtils.isBlank(s.getAislng())) 
						&& (StringUtils.isBlank(s.getGpslat()) || StringUtils.isBlank(s.getGpslng())))){
						ShipMap map = new ShipMap();
						map.setsId(s.getShipCode());
						map.setsName(s.getShipName());
						map.setsStatus(Integer.parseInt(s.getShipStatus()));
						map.setHeading(BaseUtil.getFloat(s.getShipDirection(), 0.0f));
						map.setsLatitude(BaseUtil.getDouble(s.getAislat(), 0.0));
						map.setsLongitude(BaseUtil.getDouble(s.getAislng(), 0.0));
						map.setsGpsLatitude(BaseUtil.getDouble(s.getGpslat(), 0.0));
						map.setsGpsLongitude(BaseUtil.getDouble(s.getGpslng(), 0.0));
						map.setUtc(System.currentTimeMillis());
						ShipInfo linkship = adhoc.getShip().get(linkShipCode);
						if(null != linkship){
							map.setlLatitude(BaseUtil.getDouble(linkship.getAislat(), 0.0));
							map.setlLongitude(BaseUtil.getDouble(linkship.getAislng(), 0.0));
							map.setlGpsLatitude(BaseUtil.getDouble(linkship.getGpslat(), 0.0));
							map.setlGpsLongitude(BaseUtil.getDouble(linkship.getGpslng(), 0.0));
						}
						list.add(map);
				}
			}
			//添加AIS实时数据，并过滤重复数据
			List<AisRealData> aisList = AisRealDataEhcache.getAll();
			for (AisRealData ais : aisList) {
				HashMap<String, ShipInfo> smap = adhoc.getShip();
				for (String key : smap.keySet()) {
					if(ais.getMmsi().equals(smap.get(key))){
						aisList.remove(ais);
						break;
					}
				}
			}
			for (AisRealData ais : aisList) {
				ShipMap map = new ShipMap();
				map.setsId(ais.getMmsi());
				map.setsName(ais.getShipName());
				map.setsStatus(0);
				map.setHeading(BaseUtil.getFloat(ais.getHeading(), 0.0f));
				map.setsLatitude(BaseUtil.getDouble(ais.getLatitude(), 0.0));
				map.setsLongitude(BaseUtil.getDouble(ais.getLongitude(), 0.0));
				map.setsGpsLatitude(0.00);
				map.setsGpsLongitude(0.00);
				map.setUtc(System.currentTimeMillis());
				
				list.add(map);
			}
		} 
		//没有组网时，只显示AIS实时数据
		if(null == adhocList || adhocList.size() == 0){
			
			List<AisRealData> aisList = AisRealDataEhcache.getAll();
			for (AisRealData ais : aisList) {
				ShipMap map = new ShipMap();
				map.setsId(ais.getMmsi());
				map.setsName(ais.getShipName());
				map.setsStatus(0);
				map.setHeading(BaseUtil.getFloat(ais.getHeading(), 0.0f));
				map.setsLatitude(BaseUtil.getDouble(ais.getLatitude(), 0.0));
				map.setsLongitude(BaseUtil.getDouble(ais.getLongitude(), 0.0));
				map.setsGpsLatitude(0.00);
				map.setsGpsLongitude(0.00);
				map.setUtc(System.currentTimeMillis());
				
				list.add(map);
			}
		}
		
		//LOGGER.info(">>>>>>>>>>>>:" + JSONObject.toJSONString(list));
		return list;
	}
	
	/**
	 * 获取组网树菜单数据
	 * @return
	 */
	/*public List<zTreeVo> getAdhocTree(){
		
		List<zTreeVo> zTreeVoList = new ArrayList<>();
		
		List<AdhocInfo> adhocList = getAllAdhoc();
		
		for (AdhocInfo adhoc : adhocList) {
			zTreeVo vo = new zTreeVo();
			vo.setId(adhoc.getAdhocCode());
			vo.setPId("0");
			vo.setName(adhoc.getAdhocName());
			zTreeVoList.add(vo);
			String parentid = adhoc.getAdhocCode();
			for (ShipInfo ship : adhoc.getShips()) {
				zTreeVo svo = new zTreeVo();
				svo.setId(ship.getShipCode());
				svo.setName(ship.getShipName());
				svo.setPId(parentid);
				zTreeVoList.add(svo);
				String sparentid = ship.getShipCode();
				for (Aerials aerials : ship.getAerialsList()) {
					zTreeVo avo = new zTreeVo();
					avo.setId(aerials.getAerialsCode());
					avo.setName(aerials.getAerialsCode());
					avo.setPId(sparentid);
					zTreeVoList.add(avo);
				}
			}
			
		}
		return zTreeVoList;
	}*/
	/**
	 * 根据组网编号和船站编号获取船站信息
	 * @param adhocCode
	 * @param shipCode
	 * @return
	 */
	public ShipInfo getShipInfoByCode(String adhocCode,String shipCode){
		
		AdhocInfo adhoc = getAdhocByCode(adhocCode);
		
		if(null == adhoc) return null;
		
		HashMap<String,ShipInfo> ships = adhoc.getShip();
		
		return ships.get(shipCode);
	}
	/**
	 * 根据船站编号获取其信息
	 * @param shipCode
	 * @return
	 */
	public ShipInfo getShipInfoByShipCode(String shipCode){
		
		List<ShipInfo> ships = getAllShipInfo();
		
		for (ShipInfo ship : ships) {
			if(shipCode.equals(ship.getShipCode())){
				return ship;
			}
		}
		return null;
	}
}

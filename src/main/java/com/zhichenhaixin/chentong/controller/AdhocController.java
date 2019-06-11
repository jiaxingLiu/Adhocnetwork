package com.zhichenhaixin.chentong.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zhichenhaixin.chentong.model.adhoc.AdhocInfo;
import com.zhichenhaixin.chentong.model.adhoc.ShipLinkVo;
import com.zhichenhaixin.chentong.model.map.ShipMap;
import com.zhichenhaixin.chentong.model.ships.ShipInfo;
import com.zhichenhaixin.chentong.service.AdhocService;
import com.zhichenhaixin.chentong.utils.ResultMsgUtil;

/**
 * 组网接口服务类
 * @version V1.0
 * @author pwl
 * @date 2019年5月11日10:13:3
 * @Description 
 */
@RestController
public class AdhocController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdhocController.class);
	
	@Autowired
	AdhocService adhocService;
	
	/**
	 * 获取本船的所有信息
	 * @return
	 */
	@PostMapping(value = "adhoc/getOneselfShipInfo")
	public JSONObject getOneselfShipInfo(){
		ShipInfo shipInfo = adhocService.getOneselfShipInfo();
		if(null == shipInfo){
			return ResultMsgUtil.error("没有查询到记录");
		}
		return ResultMsgUtil.success(JSONObject.toJSONString(shipInfo));
	}
	/**
	 * 获取所有组网信息
	 * @return
	 */
	@PostMapping(value = "adhoc/getAllAdhoc")
	public JSONObject getAllAdhoc(){
		List<AdhocInfo> adhocList = adhocService.getAllAdhoc();
		if(null == adhocList || adhocList.size() == 0){
			return ResultMsgUtil.error("没有查询到记录");
		}
		return ResultMsgUtil.success(JSONObject.toJSONString(adhocList));
	}
	/**
	 * 获取所有组网下的所有船站信息
	 * @return
	 */
	@PostMapping(value = "adhoc/getAllShipInfo")
	public JSONObject getAllShipInfo(){
		List<ShipInfo> list = adhocService.getAllShipInfo();
		if(null == list || list.size() == 0){
			return ResultMsgUtil.success(ResultMsgUtil.tableData(0, list));
		}
		return ResultMsgUtil.success(ResultMsgUtil.tableData(list.size(), list));
	}
	/**
	 * 获取组网树形菜单
	 * @return
	 */
/*	@PostMapping(value = "adhoc/getAllAdhocTree")
	public JSONObject getAllAdhocTree(){
		List<zTreeVo> list = adhocService.getAdhocTree();
		if(null == list || list.size() == 0){
			return ResultMsgUtil.success(ResultMsgUtil.tableData(0, list));
		}
		return ResultMsgUtil.success(ResultMsgUtil.tableData(list.size(), list));
	}*/
	/**
	 * 根据组网编号获取组网信息
	 * @param adhocCode
	 * @return
	 */
	@PostMapping(value = "adhoc/getAdhocByCode")
	public JSONObject getAdhocByCode(@RequestBody String adhocCode){
		
		List<ShipLinkVo> ships = adhocService.getShipLinkVo(adhocCode);
		if(null == ships  && ships.size() > 0){
			return ResultMsgUtil.success(ResultMsgUtil.tableData(ships.size(), ships));
		}
		return ResultMsgUtil.success(ResultMsgUtil.tableData(0, ships));
	}
	/**
	 * 根据组网编号和船站编号获取船站信息
	 * @param param
	 * @return
	 */
	@PostMapping(value = "adhoc/getShipInfoByCode")
	public JSONObject getShipInfoByCode(@RequestBody Map<String,String> param){
		String adhocCode = param.get("adhocCode");
		String shipCode = param.get("shipCode");
		ShipInfo ship = adhocService.getShipInfoByCode(adhocCode,shipCode);
		List<ShipInfo> ships = null;
		if(null != ship ){
			ships = new ArrayList<>();
			ships.add(ship);
			return ResultMsgUtil.success(ResultMsgUtil.tableData(ships.size(), ships));
		}
		return ResultMsgUtil.success(ResultMsgUtil.tableData(0, ships));
	}
	/**
	 * 根据船站编号获取其信息
	 * @param shipCode
	 * @return
	 */
	@PostMapping(value = "adhoc/getShipInfoByShipCode")
	public JSONObject getShipInfoByShipCode(@RequestBody String shipCode){

		ShipInfo ship = adhocService.getShipInfoByShipCode(shipCode);
		List<ShipInfo> ships = null;
		if(null != ship ){
			ships = new ArrayList<>();
			ships.add(ship);
			return ResultMsgUtil.success(ResultMsgUtil.tableData(ships.size(), ships));
		}
		return ResultMsgUtil.success(ResultMsgUtil.tableData(0, ships));
	}
	/**
	 * 获取组网信息：图表形式
	 * @return
	 */
	@PostMapping(value = "adhoc/getAdhocVo")
	public JSONObject getAdhocVo(){
		
		List<AdhocInfo> list = adhocService.getAllAdhoc();
		if(null != list ){
			
			return ResultMsgUtil.success(ResultMsgUtil.tableData(list.size(), list));
		}
		list = new ArrayList<>();
		return ResultMsgUtil.success(ResultMsgUtil.tableData(0, list));
	}
	/**
	 * 获取船站信息（地图显示用）
	 * @return
	 */
	@PostMapping(value = "adhoc/getShipMap")
	public JSONObject getShipMap(){
		
		List<ShipMap> list = adhocService.getShipMap();
		if(null != list  && list.size() > 0){
			return ResultMsgUtil.success(ResultMsgUtil.tableData(list.size(), list));
		}
		return ResultMsgUtil.success(ResultMsgUtil.tableData(0, list));
	}
}

package com.zhichenhaixin.chentong.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zhichenhaixin.chentong.utils.ResultMsgUtil;
 
/**
 * 捕获异常的类
 * @version V1.0
 * @author pwl
 * @date 2019年5月11日9:13:3
 * @Description 
 */
@ControllerAdvice
public class ExceptionHandle {

	// 使用 spring 自带的org.slf4j
	private final static Logger log = LoggerFactory.getLogger(ExceptionHandle.class);

	@ExceptionHandler(value= Exception.class)
	@ResponseBody
	public JSONObject handle(Exception e) {
		// 记录日志
		log.error("我来记录日志 产生异常:{}",e.getStackTrace().toString());
		// 返回异常
		return ResultMsgUtil.error(e.getMessage());
	}
}

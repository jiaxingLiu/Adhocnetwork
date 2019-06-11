package com.zhichenhaixin.chentong.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.zhichenhaixin.chentong.model.User;
import com.zhichenhaixin.chentong.service.UserService;
import com.zhichenhaixin.chentong.utils.ResultMsgUtil;
/**
 * 用户登陆
 * @version V1.0
 * @author pwl
 * @date 2019年5月11日10:13:3
 * @Description 
 */
@RestController
public class LoginController { 
	
	@Autowired 
	private UserService userService;
	
	@PostMapping(value = "user/login")
	public JSONObject login(@RequestBody User user){
		
		if (userService.checkLogin(user.getuAccount(), user.getuPwd())) {			
			// 保存用户session
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			request.getSession().setAttribute("user", userService.getUser(user.getuAccount()));
			return ResultMsgUtil.success();
		}	
		
		return ResultMsgUtil.error("用户名或密码错误");
	}

}

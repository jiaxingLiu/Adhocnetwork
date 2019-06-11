package com.zhichenhaixin.chentong.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhichenhaixin.chentong.model.User;
import com.zhichenhaixin.chentong.utils.ResourceUtil;


/**
 * 跳转页面的Controller
 * 注解一定要写Controller,RestController会直接
 * 返回文本消息给页面。
 * @version V1.0
 * @author pwl
 * @date 2018年6月6日 上午10:24:29
 * @Description 
 */
@Controller
public class PageController {
	 

	 @RequestMapping("/adhoctables")
     public String adhoctables(){
		//验证是否登陆
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		User user = (User)request.getSession().getAttribute("user");
		if(user != null  && user.getuAccount().equals(ResourceUtil.userName)
				&& user.getuPwd().equals(ResourceUtil.userPassword)){
			 return "/adhoctables";
		}
    	return "/login";
     }

	 @RequestMapping("/shiptables")
     public String shiptablesHtml(){
		//验证是否登陆
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		User user = (User)request.getSession().getAttribute("user");
		if(user != null  && user.getuAccount().equals(ResourceUtil.userName)
				&& user.getuPwd().equals(ResourceUtil.userPassword)){
			 return "/shiptables";
		}
    	 return "/login";
     }
	 
	 @RequestMapping("/antenna")
     public String sectiontablesHtml(){
		//验证是否登陆
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		User user = (User)request.getSession().getAttribute("user");
		if(user != null  && user.getuAccount().equals(ResourceUtil.userName)
				&& user.getuPwd().equals(ResourceUtil.userPassword)){
			 return "/antenna";
		}
    	return "/login";
     }
	 
	 @RequestMapping("/map")
     public String aistablesHtml(){
		//验证是否登陆
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		User user = (User)request.getSession().getAttribute("user");
		if(user != null  && user.getuAccount().equals(ResourceUtil.userName)
				&& user.getuPwd().equals(ResourceUtil.userPassword)){
			 return "/map";
		}
    	return "/login";
     }
	 
	 @RequestMapping("/home")
     public String homeHtml(){
		//验证是否登陆
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		User user = (User)request.getSession().getAttribute("user");
		if(user != null  && user.getuAccount().equals(ResourceUtil.userName)
				&& user.getuPwd().equals(ResourceUtil.userPassword)){
			 return "/home";
		}
    	return "/login";
     }
	 
	 @RequestMapping("/login")
     public String loginHtml(){
    	 return "/login";
     }
	 @RequestMapping("/mytest")
	 public String mytest(){
		 return "/mytest";
	 }
	 
	 /**
	 * 配置文件上传页面
	 * 
	 * @param map
	 * @return 
	 * @author zhangbohu
	 */
	@RequestMapping("/uploadtest")
     public String uploadtestHtml(HashMap<String, Object> map){
    	 return "/uploadtest";
     }
}

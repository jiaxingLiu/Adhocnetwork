package com.zhichenhaixin.chentong.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 日志切面
 * @version V1.0
 * @author pwl
 * @date 2019年5月11日9:13:3
 * @Description 
 */
@Aspect
@Component
public class HttpAspect {
	
	// 使用 spring 自带的org.slf4j
	private final static Logger log =  LoggerFactory.getLogger(HttpAspect.class);
	
	/**
	 * 
	 * @param joinPoint 通过该参数可以获取类方法等信息
	 * @author pwl
	 */
	@Pointcut("execution(* com.zhichenhaixin.chentong.controller..*.*(..))")
	public void log() {
		
	}
	
	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		//log.info("进来controller了");
		
		// 获取request对象
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		
		HttpServletRequest request = attributes.getRequest();
		 
		//URL
		log.info("url={}",request.getRequestURI());
		
		//method
		log.info("method={}",request.getMethod());
		 
		//ip
		log.info("ip={}",request.getRemoteAddr());
		
		//类方法
		log.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."
				+joinPoint.getSignature().getName());
		//参数
		log.info("args={}",joinPoint.getArgs());
		 
	}
	
	@After("log()")
	public void doAfter() {
		log.info("记录一次记录完成");
	}
	/**
	 * 獲取方法的返回值
	 * 
	 * @param object 
	 * 
	 */
	@AfterReturning(returning = "object", pointcut="log()")
	public void doAfterReturning(Object object) {
		log.info("response={}",object.toString());
	}
}

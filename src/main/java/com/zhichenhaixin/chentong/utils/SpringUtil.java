package com.zhichenhaixin.chentong.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取Spring容器中bean的方法
 * @version V1.0
 * @author pwl
 * @date 2018年6月15日 下午2:02:20
 * @Description 
 */
@Component
public class SpringUtil implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext = null; 
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		if(SpringUtil.applicationContext == null){
			SpringUtil.applicationContext = applicationContext;
		}
		System.out.println("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()"
				+ "获取applicationContext对象,applicationContext="+
		    SpringUtil.applicationContext+"========");
	}
	
	//获取applicationContext
	public static ApplicationContext getApplicationContext(){
		 return applicationContext;
	}
	
	//通过name获取bean
	public static Object getBean(String name){
		return getApplicationContext().getBean(name);
	}
	
	//通过class获取bean
	public static <T> T getBean(Class<T> clazz){
		return getApplicationContext().getBean(clazz);
	}
	
	//通过name,以及class返回指定的bean
	public static <T> T getBean(String name,Class<T> clazz){
		return getApplicationContext().getBean(name,clazz);
	}
}

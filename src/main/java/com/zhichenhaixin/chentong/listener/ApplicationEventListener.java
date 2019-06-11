package com.zhichenhaixin.chentong.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

import com.zhichenhaixin.chentong.constant.Constant;

/**
 * 监听应用生命周期的监听器
 * @version V1.0
 * @author pwl
 * @date 2019年5月11日9:13:3
 * @Description 
 */
public class ApplicationEventListener implements ApplicationListener {

	/** log日志  */
	private Logger LOGGER = LoggerFactory.getLogger(ApplicationEventListener.class);

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// 在这里可以监听到Spring Boot的生命周期
		// 初始化环境变量
		if (event instanceof ApplicationEnvironmentPreparedEvent) {

			LOGGER.info("=====  应用监听:初始化环境变量    =====");
		}
		// 初始化完成
		if (event instanceof ApplicationPreparedEvent) {
			
			LOGGER.info("=====  应用监听: 初始化完成     =====");
		}
		// 应用刷新
		if (event instanceof ContextRefreshedEvent) {

			LOGGER.info("=====  应用监听:应用刷新     =====");
		}
		// 应用已启动完成
		if (event instanceof ApplicationReadyEvent) {

			LOGGER.info("=====  应用监听:应用已启动完成    =====");
		}
		// 应用启动，需要在代码动态添加监听器才可捕获
		if (event instanceof ContextStartedEvent) {

			LOGGER.info("=====  应用监听:应用启动    =====");
		}
		// 应用停止
		if (event instanceof ContextStoppedEvent) {

			

			LOGGER.info("=====  应用监听:应用停止    =====");
		}
		// 应用关闭
		if (event instanceof ContextClosedEvent) {
			//List<LapControllerMate> ctrlList = 
			// 设置线程运行的标志位false
			Constant.THREADISRUNING = false;

			// 关闭进程
			//System.exit(0);
			LOGGER.info("=====  应用监听:应用关闭      =====");
		}
	}

	 
}
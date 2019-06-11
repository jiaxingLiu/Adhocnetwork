package com.zhichenhaixin.chentong.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.zhichenhaixin.chentong.service.SysInitService;

/**
 * 项目启动初始化资源操作 在SpringBeans都初始化之后， SpringApplication.run()之前执行
 * 
 * @version V1.0
 * @author pwl
 * @date 2018年6月5日 上午11:42:21
 * @Description
 */
@Component
@Order(1)
public class SysInitThread implements CommandLineRunner {

	 
	/** 进行数据初始化的服务  */
	@Autowired
	private SysInitService sysInitService;

	@Override
	public void run(String... args) throws Exception {

		// 进行系统初始化的一些操作
		// System.out.println("系统初始化操作--sysInitThread--开始");
		// new StationMateThread(111,lapService).start();
		// 读取配置文件中的配置信息
		sysInitService.initResourceUtil();

		//初始化地图扇区
		//sysInitService.initAreaPolygon();
		
		// 开启系统需要的线程
		//sysInitService.startThreads();

		// 读取配置文件中的配置信息
		// sysInitService.startThread();

		// 开启测试的线程
		// TestPointThread test = new TestPointThread();
		// test.start();
		//开启SocketServer线程监听客户端推送数据
		SocketServerThread sst = new SocketServerThread();
		sst.start();

		// zbh:先将缓存中内容清空一遍, 在进行添加操作, 保证在大网管端被删除的数据不会留在缓存中

	}

}

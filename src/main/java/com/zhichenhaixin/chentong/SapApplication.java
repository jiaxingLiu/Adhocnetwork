package com.zhichenhaixin.chentong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@MapperScan("com.zhichenhaixin.chentong.dao")
@ComponentScan
@EnableScheduling //定时器启动注解
public class SapApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		//System.setProperty("java.io.tmpdir", "C:\\Users\\HP\\AppData\\Local\\Temp\\");
		SpringApplication.run(SapApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SapApplication.class);
	}
}

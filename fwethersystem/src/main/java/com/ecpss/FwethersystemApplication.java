package com.ecpss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@ImportResource(locations = {"classpath:citylist.xml"})
/**
 * 面向服务的架构（SOA）
 */

/**
 * 服务拆分
 * 服务注册
 * 服务发现
 * 服务消费
 * ----统一入口
 * 配置管理
 * 熔断机制
 * 自动扩展
 * 微服务的拆分和意义
 * 易于实现（代码量变少）
 * 易于维护
 * 易于部署
 * 易于更新
 *
 */
public class FwethersystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FwethersystemApplication.class, args);
	}

}

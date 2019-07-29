package com.ecpss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaServer
@EnableTransactionManagement
public class ZzjavaApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(ZzjavaApplication.class, args);
	}

}

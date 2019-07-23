package com.ecpss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ZzjavaApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(ZzjavaApplication.class, args);
	}

}

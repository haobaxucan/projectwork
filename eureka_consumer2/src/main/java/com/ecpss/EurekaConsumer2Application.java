package com.ecpss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaConsumer2Application {
	/**
	 * demo
	 *
	 * http://localhost:8003/buy?name=xxcc
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(EurekaConsumer2Application.class, args);
	}

}

package com.ecpss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(value = "com.ecpss.mapper")
@SpringBootApplication
public class HrbacApplication {
	public static void main(String[] args) {
		SpringApplication.run(HrbacApplication.class, args);
	}

}

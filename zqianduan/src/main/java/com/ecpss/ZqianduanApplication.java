package com.ecpss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ZqianduanApplication {

	public static void main(String[] args) {
		log.info("sss");
		SpringApplication.run(ZqianduanApplication.class, args);
	}

}
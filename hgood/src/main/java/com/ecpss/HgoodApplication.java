package com.ecpss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class HgoodApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(HgoodApplication.class, args);
	}

}

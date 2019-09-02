package com.ecpss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@EnableCaching
@MapperScan("com.ecpss.boot_shiro.mapper")
@SpringBootApplication

public class HshiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(HshiroApplication.class, args);
		
	}

}

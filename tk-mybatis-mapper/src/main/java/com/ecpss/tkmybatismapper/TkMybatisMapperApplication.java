package com.ecpss.tkmybatismapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TkMybatisMapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(TkMybatisMapperApplication.class, args);
    }

}

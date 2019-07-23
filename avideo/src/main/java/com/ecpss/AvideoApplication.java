package com.ecpss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AvideoApplication {
    
    public static void main(String[] args) {
        
        SpringApplication.run(AvideoApplication.class, args);
    }
    
}

package com.ecpss.config;

import com.ecpss.listener.Mylistener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * Created by xc on 2019/8/12.
 */
@Configuration
public class Myconfig {
    @Bean
    public ServletListenerRegistrationBean mylistener(){
        ServletListenerRegistrationBean registrationBean=new ServletListenerRegistrationBean(new Mylistener());
        return registrationBean;
    }
}

package com.ecpss.spring.domain.instance;

import com.ecpss.test.user.User;
import org.springframework.context.annotation.*;

/**
 *
 * Created by xc on 2019/7/20.
 */

@Configuration
@ComponentScan
@ImportResource(locations = "classpath:bean.xml")
public class Mainconfig {
    /**
     * 默认使用方法的名称
     * @return
     */
    @Bean
    public User getUser(){
        return new User();
    }
}

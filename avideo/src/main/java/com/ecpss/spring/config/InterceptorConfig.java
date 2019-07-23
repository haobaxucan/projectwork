package com.ecpss.spring.config;

import com.ecpss.spring.domain.User;
import com.ecpss.utils.JwtUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by xc on 2019/6/20.
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        /**拦截所有
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/red");
    }
    
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        User user=new User();
        user.setId(12);
        user.setName("xc");
        user.setHeadImg("www.html.com");
        String token = JwtUtils.geneJsonWebToken(user);
//        registry.addViewController("/re").setViewName("index");
    }
}

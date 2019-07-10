package com.ecpss.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created by xc on 2019/6/17.
 */
@Configuration
public class WebSocketConfig {
    /**
     *大坑
     使用外部tomcat容器启动websocket
     1.删除ServerEndpointExporter配置bean
     2.接收连接的类删除@Component
     * @return
     */
//    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        
        return new ServerEndpointExporter();
    }
    
}

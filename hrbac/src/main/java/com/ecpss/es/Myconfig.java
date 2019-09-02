package com.ecpss.es;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * Created by xc on 2019/9/1.
 */
@Configuration
public class Myconfig {

    public TransportClient transportClient() {
       
        TransportClient transportClient = null;
        try {
            // 配置信息
            Settings esSetting = Settings.builder()
                    .put("cluster.name", "my-application") //集群名字
                    .build();
            transportClient = new PreBuiltTransportClient(esSetting);
            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName("localhost"), 9300);
            transportClient.addTransportAddresses(transportAddress);
        } catch (Exception e) {
        
        }
        return transportClient;
    }


}

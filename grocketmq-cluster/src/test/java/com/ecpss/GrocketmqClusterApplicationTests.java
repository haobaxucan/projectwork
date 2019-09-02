package com.ecpss;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrocketmqClusterApplicationTests {
	@Autowired
	private DefaultMQProducer defaultMQProducer;

	@Test
	public void contextLoads() throws Exception{
		String msg = "demo msg test";
		Message sendMsg = new Message("xc","add",msg.getBytes());
		SendResult send = defaultMQProducer.send(sendMsg);
		System.out.println(send.getMsgId());
		
	}

}

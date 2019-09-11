package com.ecpss;

import com.ecpss.mq.Activemq_producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableScheduling
public class HactivemqApplicationTests {
	
	@Autowired
	Activemq_producer producer;
	
	@Test
	public void mq() throws Exception {
		producer.produceMessage();
//		producer.produceMessageScheduled();
	}

}

package com.ecpss;

import com.ecpss.mq.Activemq_producer;
import com.ecpss.utils.ActiveMQUtil;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableScheduling
public class HactivemqApplicationTests {
	
	@Autowired
	Activemq_producer producer;
	@Autowired
	ActiveMQUtil activeMQUtil;
	
	@Test
	public void mq() throws Exception {
		Connection connection = null;
		Session session = null;
		try {
			 connection = activeMQUtil.getConnectionFactory().createConnection();
			session = connection.createSession(true, Session.SESSION_TRANSACTED);
			
			Queue queue = session.createQueue("q");
			MessageProducer producer = session.createProducer(queue);
			MapMessage mapMessage = new ActiveMQMapMessage();// hash结构
			
			mapMessage.setString("out_trade_no","1111111111111");
			
			
			producer.send(mapMessage);
			
			session.commit();
		} catch (Exception e1) {
			session.rollback();
			connection.close();
		}
		
	}

}

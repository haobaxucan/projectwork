package com.ecpss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrocketmqClusterApplication {
	/**
	 * rocketmq 消息保存到磁盘中
	 * @param args
	 */
	/**
	 * 提供者端的重试
	 *
	 * 消费者端的重试
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 *顺序消费
		 *消费组的作用
		 * 一个消费组里有多个消费者
		 * 消费者消费模式:集群  订阅模式
		 * 集群: 几个消费者消费的消息是所有的总和 比如1000数据 向一个消费者组的三个消费者发送，三个消费者一个200,500,300
		 *设置消费模式 在消费者端   consumer.setMessageModel(MessageModel.CLUSTERING);
		 */
		
		/**
		 * 重复消息
		 *
		 * 可能由于某种原因 服务端发送消息的时候，同一条消息发送了两次，多次--消费者端不知道--进行了重复消息
		 *
		 * 必须在消费者端解决这个问题？
		 * 两种解决方案--
		 * 1保持业务的幂等型
		 * 2 用redis  进行消息处理
		 */
		SpringApplication.run(GrocketmqClusterApplication.class, args);
	}

}

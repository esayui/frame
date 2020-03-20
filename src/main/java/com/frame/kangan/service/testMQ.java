package com.frame.kangan.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.frame.kangan.config.RabbitMQConfig;
import com.frame.kangan.data.po.FrameUser;


@Service
public class testMQ {

	@RabbitListener(queues = RabbitMQConfig.Frame_QUEUE)
	public void testMQsdf(FrameUser message) throws Exception{




		System.out.println(message);
	}
}

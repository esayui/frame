package com.frame.rengu.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.config.StatefulRetryOperationsInterceptorFactoryBean;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.retry.interceptor.StatefulRetryOperationsInterceptor;
import org.springframework.util.DigestUtils;

/**
 * @ClassName: RabbitMQConfig
 * 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author kang.an@ele.me
 * 
 * @date 2016年6月28日 下午6:25:15
 * 
 */
@SpringBootApplication
public class RabbitMQConfig {
	public static final String TOPIC_QUEUE1 = "topic.control";
	public static final String TOPIC_QUEUE2 = "topic.eng";
	public static final String TOPIC_QUEUE3 = "topic.mov";
	public static final String TOPIC_QUEUE4 = "topic.pos";
	public static final String TOPIC_EXCHANGE = "topic.exchange";

	/**
	 * Topic模式
	 * @return
	 */
	@Bean
	public Queue topicQueue1() {
		return new Queue(TOPIC_QUEUE1);
	}
	@Bean
	public Queue topicQueue2() {
		return new Queue(TOPIC_QUEUE2);
	}

	@Bean
	public Queue topicQueue3() {
		return new Queue(TOPIC_QUEUE3);
	}
	@Bean
	public Queue topicQueue4() {
		return new Queue(TOPIC_QUEUE4);
	}
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(TOPIC_EXCHANGE);
	}
	@Bean
	public Binding topicBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("key.#control");
	}
	@Bean
	public Binding topicBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("key.#eng");
	}

	@Bean
	public Binding topicBinding3() {
		return BindingBuilder.bind(topicQueue3()).to(topicExchange()).with("key.#mov");
	}
	@Bean
	public Binding topicBinding4() {
		return BindingBuilder.bind(topicQueue4()).to(topicExchange()).with("key.#pos");
	}




}

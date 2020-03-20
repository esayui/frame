package com.frame.rengu.listener;


import com.frame.rengu.config.RabbitMQConfig;
import com.frame.rengu.data.po.*;
import com.frame.rengu.web.socket.MyWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitRecvThread {






    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE1)
    public void receiveTopic1(TYPE_J_CONTROL content) {
        logger.info("【receiveTopic1监听到消息】" + content);

        try {
            MyWebSocket.sendInfo(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(TYPE_J_ENG content) {
        logger.info("【receiveTopic2监听到消息】" + content);
    }
    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE3)
    public void receiveTopic3(TYPE_J_MOV content) {
        logger.info("【receiveTopic3监听到消息】" + content);
    }
    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE4)
    public void receiveTopic4(TYPE_J_POS content) {
        logger.info("【receiveTopic4监听到消息】" + content);
    }



}

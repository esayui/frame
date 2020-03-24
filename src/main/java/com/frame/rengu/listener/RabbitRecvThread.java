package com.frame.rengu.listener;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.frame.rengu.config.RabbitMQConfig;
import com.frame.rengu.data.mapper.TYPE_J_CONTROLMapper;
import com.frame.rengu.data.mapper.TYPE_J_ENGMapper;
import com.frame.rengu.data.mapper.TYPE_J_MOVMapper;
import com.frame.rengu.data.mapper.TYPE_J_POSMapper;
import com.frame.rengu.data.po.*;

import com.frame.rengu.web.socket.MyWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Component
public class RabbitRecvThread {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static RabbitRecvThread rabbitRecvThread = null;

    @Autowired
    TYPE_J_CONTROLMapper type_j_controlMapper;

    @Autowired
    TYPE_J_ENGMapper type_j_engMapper;

    @Autowired
    TYPE_J_MOVMapper type_j_movMapper;

    @Autowired
    TYPE_J_POSMapper type_j_posMapper;



    @PostConstruct
    public void init(){


    }


    public RabbitRecvThread(){
        rabbitRecvThread = this;

    }


    // queues是指要监听的队列的名字
//    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE1)
//    public void receiveTopic1(Message message) {
//        String body = new String(message.getBody());
//
//
//        TYPE_J_CONTROL data = JSON.parseObject(body,TYPE_J_CONTROL.class);
//
//        rabbitRecvThread.type_j_controlMapper.insert(data);
//
//        logger.info("【receiveTopic1监听到消息】" + data.toString());
//
//        sendToWebSocket(body);
//
//
//    }
    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(Message message) {
        String body = new String(message.getBody());


        TYPE_J_ENG data = JSON.parseObject(body,TYPE_J_ENG.class);

        rabbitRecvThread.type_j_engMapper.insert(data);

        logger.info("【receiveTopic2监听到消息】" + data.toString());


        JSONObject result = new JSONObject();
        result.put("type","eng");
        result.put("data",data);

        sendToWebSocket(result.toJSONString());
    }
//    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE3)
//    public void receiveTopic3(Message message) {
//        String body = new String(message.getBody());
//
//
//        TYPE_J_MOV data = JSON.parseObject(body,TYPE_J_MOV.class);
//
//        rabbitRecvThread.type_j_movMapper.insert(data);
//
//        logger.info("【receiveTopic3监听到消息】" + data.toString());
//
//        sendToWebSocket(body);
//    }

    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE4)
    public void receiveTopic4(Message message) {
        String body = new String(message.getBody());


        TYPE_J_POS data = JSON.parseObject(body,TYPE_J_POS.class);

        rabbitRecvThread.type_j_posMapper.insert(data);

        logger.info("【receiveTopic4监听到消息】" + data.toString());

        JSONObject result = new JSONObject();
        result.put("type","pos");
        result.put("data",data);

        sendToWebSocket(result.toJSONString());    }


    public void sendToWebSocket(String jsonStr){

        //未联通前段前先注释掉
        try {
            MyWebSocket.sendInfo(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}

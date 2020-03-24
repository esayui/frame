package com.frame.rengu.web.socket;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.frame.rengu.config.RabbitMQConfig;
import com.frame.rengu.data.mapper.TYPE_J_CONTROLMapper;
import com.frame.rengu.data.mapper.TYPE_J_MOVMapper;
import com.frame.rengu.data.po.TYPE_J_CONTROL;
import com.frame.rengu.data.po.TYPE_J_MOV;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.*;
import org.yeauty.pojo.ParameterMap;
import org.yeauty.pojo.Session;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint( prefix = "netty-websocket")
@Component
public class MyWebSocket {

    private static Map<Session,String> webSocketSet = new HashMap<Session, String>();


    public static MyWebSocket myWebSocket = null;


    @Autowired
    TYPE_J_CONTROLMapper type_j_controlMapper;

    @Autowired
    TYPE_J_MOVMapper type_j_movMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;


    @PostConstruct
    public void init(){}

    public MyWebSocket(){
        myWebSocket = this;
    }


    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, ParameterMap parameterMap) throws IOException {
        System.out.println("new connection");




    }

    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("one connection closed");

        webSocketSet.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) {

        webSocketSet.put(session,"");

        JSONObject jsonObject = JSON.parseObject(message);
        if(jsonObject.get("type").equals("control")){
            TYPE_J_CONTROL type_j_control = JSON.parseObject(jsonObject.get("data").toString(),TYPE_J_CONTROL.class);
            myWebSocket.type_j_controlMapper.insert(type_j_control);
            rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "key.#control",type_j_control);


        }else if(jsonObject.get("type").equals("mov")){
            TYPE_J_MOV type_j_mov = JSON.parseObject(jsonObject.get("data").toString(), TYPE_J_MOV.class);
            myWebSocket.type_j_movMapper.insert(type_j_mov);
            rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "key.#mov",type_j_mov);

        }



        System.out.println(message);
        //session.sendText("Hello World!");
    }





    public static void sendInfo(String message) throws IOException {
       // log.info("推送消息到窗口"+sid+"，推送内容:"+message);
        webSocketSet.entrySet().forEach(p->{
            sendMessage(p.getKey(),message);
        });
    }

    public static void sendMessage(Session session, String message) {
        if(session != null){
            session.sendText(message);
        }
    }



    private void sendMessage(String message){


    }


    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        for (byte b : bytes) {
            System.out.println(b);
        }
        session.sendBinary(bytes);
    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("write idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    break;
                default:
                    break;
            }
        }
    }

}


package com.frame.rengu.web.socket;


import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.*;
import org.yeauty.pojo.ParameterMap;
import org.yeauty.pojo.Session;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(prefix = "netty-websocket")
@Component
public class MyWebSocket {

    private static Map<Session,String> webSocketSet = new HashMap<Session, String>();


    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, ParameterMap parameterMap) throws IOException {
        System.out.println("new connection");

        String paramValue = parameterMap.getParameter("paramKey");
        System.out.println(paramValue);

        webSocketSet.put(session,"");

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
        System.out.println(message);
        session.sendText("Hello Netty!");
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


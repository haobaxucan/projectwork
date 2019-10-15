package com.ecpss.websocketdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by xc on 2019/6/17.
 */
@ServerEndpoint("/bb")
@Component
public class WebSocketServer {
    private Session session;
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    @OnOpen
    public void onOpen(Session session) {
        this.session=session;
        webSocketSet.add(this);
        System.out.println(session.getId()+"111");
    }


    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
    }
    

    @OnMessage
    public void onMessage(String message) throws Exception{

        System.out.println(message);//接收客户端数据
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {

        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
//                if(sid==null) {
//                    item.sendMessage(message);
//                }else if(item.sid.equals(sid)){
//                    item.sendMessage(message);
//                }
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }






    @OnError
    public void onError(Session session, Throwable error) {
//        log.error("发生错误");
        error.printStackTrace();
    }
    


}

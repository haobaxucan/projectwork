package com.ecpss.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xc on 2019/6/16.
 */
@ServerEndpoint(value = "/websocket/{name}")
public class WebSocketdemo {
    private String name; //记录当前的socket是谁
    
    private Session session; //记录当前连接
    
    private static Map<String, WebSocketdemo> allCilents = new HashMap<>();
    
 
    /**
     * @param session 当前建立连接 代表参数地址的name
     * @param name    区分连接是谁
     */
    @OnOpen  //建立连接的时候调用该方法
    public void onOpen(Session session, @PathParam("name") String name) {
        this.name = name;
        this.session = session;
        allCilents.put(name,this);
    }
    
    /**
     * 接收客户端消息
     *
     * @param session
     * @param message
     */
    @OnMessage //收到消息触发
    public void onMessage(Session session, String message) {
        System.out.println("接收客户端消息:" + message);
        JSONObject jsonObject = JSON.parseObject(message);
        String toUser = jsonObject.getString("toUser");//找到接收者
        String toMessage = jsonObject.getString("toMessage");//找到发送的内容
        WebSocketdemo webSocketdemo = allCilents.get("to");
        Session toSession = webSocketdemo.getSession();
        if(toSession.isOpen()){//如果连接是打开状态
            toSession.getAsyncRemote().sendText(toMessage); //赵傲连接的另一端，然后发送消息
        }else {
            toSession.getAsyncRemote().sendText("对方不在线");
        }
        
    }
    
    @OnError //出现异常触发
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
    
    @OnClose//连接关闭触发
    public void onClose() {
        System.out.println("关闭");
        System.out.println("有客户端关闭连接，当前在线人数为:");
    }
    public String getName() {
        return name;
    }
    
    public Session getSession() {
        return session;
    }
    
    public static Map<String, WebSocketdemo> getAllCilents() {
        return allCilents;
    }
}

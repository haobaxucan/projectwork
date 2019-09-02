package com.ecpss.redis;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by xc on 2019/7/30.
 */
public class Connetcion {

    private Socket socket;
    private String host;
    private int port;
    
    private OutputStream outputStream;
    private InputStream inputStream;
    
    public Connetcion( String host, int port) {
       
        this.host = host;
        this.port = port;
    }
    
    public Connetcion connetcion( String host, int port){
        try {
        socket=new Socket(host,port);
        outputStream=socket.getOutputStream();
        inputStream=socket.getInputStream();
        
        
        }catch (Exception e){
        
        }
        return this;
    }
    
    public Connetcion comand(String host, int port){
        connetcion( host,  port);
        
        
        return this;
    }
}

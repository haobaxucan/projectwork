package com.ecpss.redis;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xc on 2019/7/30.
 */
public class Protocol {
    
    public static final String d="x";
    
    public static void setCommand(OutputStream outputStream,Command command,byte[] ...b){
    
    
    
    }
    
    public static void main(String[] args) throws Exception{
//        ServerSocket serverSocket=new ServerSocket(6379);
//        Socket accept = serverSocket.accept();
//        byte b[]=new byte[1024];
//
//        accept.getInputStream().read(b);
//
//        System.out.println(new String(b));
//        System.out.println(command.name().length());
    
    }
    
    public static enum  Command{
        GET,SET;
    }
}

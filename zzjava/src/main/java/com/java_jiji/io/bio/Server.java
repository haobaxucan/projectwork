package com.java_jiji.io.bio;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xc on 2019/8/9.
 */
public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket socket=new ServerSocket(8005);
        System.out.println("等你上线");
        byte bs[]=new byte[1024];
        while(true){
            Socket accept = socket.accept();
            System.out.println("建立了连接");
            InputStream inputStream = accept.getInputStream();
            int read = inputStream.read(bs);
            System.out.println("我是服务器，客户端说"+new String(bs,0,read));
            accept.shutdownInput();//关闭输入流
            OutputStream outputStream = accept.getOutputStream();
            PrintWriter pw = new PrintWriter(outputStream);
            pw.println("欢迎你");
            pw.flush();
        }
    }

}

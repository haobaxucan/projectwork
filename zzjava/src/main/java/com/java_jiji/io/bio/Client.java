package com.java_jiji.io.bio;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by xc on 2019/8/9.
 */
public class Client {
    public static void main(String[] args) throws Exception{
        Socket socket=new Socket("localhost",8005);
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream);
        outputStreamWriter.write("客户端请求了");
        outputStreamWriter.flush();//注意这里必须刷新
        InputStream inputStream = socket.getInputStream();
        byte bs[]=new byte[1024];
        int read = inputStream.read(bs);
        System.out.println("我是客户端，服务器说"+new String(bs,0,read));
    }
}

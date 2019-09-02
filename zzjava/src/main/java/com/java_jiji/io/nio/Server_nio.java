package com.java_jiji.io.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by xc on 2019/8/12.
 */
public class Server_nio {
    
    public static void main(String[] args) throws Exception{
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
    
        //3. 绑定连接
        ssChannel.bind(new InetSocketAddress(9898));
        ssChannel.configureBlocking(false);//设置非阻塞
        //4. 获取选择器
        Selector selector = Selector.open();
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        
//        while (selector)
        
    
    }
}

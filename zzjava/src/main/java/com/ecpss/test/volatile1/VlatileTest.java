package com.ecpss.test.volatile1;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by xc on 2019/7/19.
 */
@Slf4j
public class VlatileTest {
    static /*volatile*/ boolean flag=false;
    
    public static void main(String[] args) throws Exception{
        
        new Thread(()->{
            log.info("等待数据准备");
            while (!flag){
            
            }
            log.info("我是超神");
        }).start();
        
        Thread.sleep(2000);
        
        new Thread(()->{
         log.info("准备数据中");
            flag=true;
            log.info("准备数据完毕");
        }).start();
        Thread.sleep(3000);
        System.out.println(flag);//最后的数据是true
//        但是第一个线程却没有感知到这个结果，第二个线程只是修改自己本地内存的变量
    }
}

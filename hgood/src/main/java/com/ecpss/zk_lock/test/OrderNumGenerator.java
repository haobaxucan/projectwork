package com.ecpss.zk_lock.test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.00
 * @date 2019/10/10
 */
public class OrderNumGenerator extends Thread {

    //区分不同的订单号
    private volatile static int count = 0;

    //单台服务器，多个线程 同事生成订单号
    public synchronized String getNumber() {
        try {
            Thread.sleep(300);
        } catch (Exception e) {
        }
        SimpleDateFormat simpt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return simpt.format(new Date()) + "-" + count++;  //时间戳后面加了 count

    }

    @Override
    public void run() {
        String number = getNumber();
        System.out.println(Thread.currentThread().getName()+"num"+number);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new OrderNumGenerator().start();
        }
    }

}

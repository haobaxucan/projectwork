package com.ecpss.ThreadT;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xc on 2019/7/15.
 */
public class ThreadT {
    //创建线程池
    ExecutorService pool = Executors.newFixedThreadPool(10);
    
    @Test
    public void test1() {
        //模拟生成100条数据
        final List<String> insertList = new ArrayList();
        for (int i = 0; i < 100; i++) {
            insertList.add("[" + i + "]");
        }
        
        for (int i = 0; i < insertList.size(); i += 20) {
            final int start = i;
            final int end = i + 20;

        pool.execute(()->this.task(insertList.subList(start,end)));
        
        }
        
        pool.shutdown();
    }
    
    public void task(List<String> insertList) {
        System.out.println(Thread.currentThread().getName() + "正在執行...第" + insertList.get(0) + "-" + insertList.get(insertList.size() - 1));
        
    }
    
    @Test
    public void test() {
        List<String> list = Lists.newArrayList("1", "2", "cc", "ddddd", "ccc");
        List<String> list1 = list.subList(2, 4);
        System.out.println(list1);
        
    }
}

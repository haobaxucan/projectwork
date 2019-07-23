package com.ecpss.test.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xc on 2019/7/19.
 */

@Slf4j
@RestController
public class jvmStock {
    /**
     * 加锁的目的 是为了序列访问资源
     *
     *
     */
    
   static Object object=new Object();//
    /**
     * 访问临界资源--是线程序列化
     * synchronized  隐式锁，不需要加锁和解锁
     */
   
    public static  int stock=5;
    public static  int i=0;
    
    @RequestMapping("/stock")
    public String aa(){
        /**
         * com.ecpss.test.lock.jvmStock@1202fcd3,com.ecpss.test.lock.jvmStock@1202fcd3
         */
//        jvmStock jvmStock=new jvmStock();  和this等效
      
//        System.out.println(jvmStock.toString()); 创建的时候肯定不能再 局部方法里创建-否则对象不一样了
         synchronized (object) {// 我们还可以使用this对象(代表当前实例)或者当前类的class对象作为锁
            /**
             *this com.ecpss.test.lock.jvmStock@5daf6497,com.ecpss.test.lock.jvmStock@5daf6497
             */
            if(stock<=0){
                log.error("商品库存不足，减库存失败,次数{},{},{}",i++,this.toString(),this);
                return "减库存失败";
            }else {
                
                stock--;
                log.error("商减库存成功,商品剩余数量{}",stock);
                return "减库存成功";
            }
        }
    
    }
    
    /**
     * 锁定是当前类对象 jvmStock 的class 对象
     */
    public static synchronized void lock(){
    
    }
    public synchronized void lock1(){//也是锁实例对象
    
    }
    
    /**
     * 原理其实就是 monitor
     */
}

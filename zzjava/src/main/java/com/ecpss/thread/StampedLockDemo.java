package com.ecpss.thread;

import java.util.concurrent.locks.StampedLock;

/**
 * Created by xc on 2019/8/7.
 */
public class StampedLockDemo {
    
    /**
     * Java8 新的读写锁的使用
     *
     * <p>新的读写锁的实现思路是在读远大于写的应用场景中读不会直接影响写，导致写操作的饥饿<p/><br/>
     * <p>读的时候可以尝试获取锁，获取不到不会直接上锁，可以循环尝试，也可以尝试一定次数后上悲观读锁</p>
     */
 
        private static int shareObj = 0;
        private static StampedLock lock = new StampedLock();
        
        private static void add() {
            //添加写锁
            long stamp = lock.writeLock();
            try {
                System.out.println("---添加写锁");
                shareObj++;
            }finally {
                lock.unlock(stamp);
                System.out.println("释放写锁");
            }
        }
        
        public static void get() {
            
            //校验获取的锁是否成功
            boolean flag = false;
            //尝试获取五次锁
            for (int i = 0; i < 5 ; i++) {
                //尝试获取读锁
                long stamp = lock.tryOptimisticRead();
                System.out.println("---添加读锁");
                if (lock.validate(stamp)) {
                    flag = true;
                    break;
                }else {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (!flag) {
                //五次都没获取到，只好加一个悲观读锁了
                long stamp = lock.readLock();
                try {
//                    System.out.println(shareObj);
                } finally {
                   
                    lock.unlockRead(stamp);
                    System.out.println("释放读锁");
                }
            } else {
                System.out.println(shareObj);
            }
        }
    
    public static void main(String[] args) {
    
        Runnable runnable1 = () -> {
            add();
        };
        Runnable runnable2 = () -> {
            get();
        };
        new Thread(runnable1).start();
        new Thread(runnable2).start();
        new Thread(runnable1).start();
        new Thread(runnable2).start();
        new Thread(runnable2).start();
        new Thread(runnable1).start();
        new Thread(runnable2).start();
        new Thread(runnable2).start();
        new Thread(runnable2).start();
        
    }
    
    }


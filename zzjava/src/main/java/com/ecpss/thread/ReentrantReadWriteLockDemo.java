package com.ecpss.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by xc on 2019/8/6.
 */
public class ReentrantReadWriteLockDemo {
    ReentrantReadWriteLock rw=new ReentrantReadWriteLock();
    
    
    public void get(){
        ReentrantReadWriteLock.ReadLock readLock=rw.readLock();
        ReentrantReadWriteLock.WriteLock writeLock=rw.writeLock();
        try {
          readLock.lock();
          writeLock.lock();
    
            System.out.println("-------");
    
    
        }finally {
        readLock.unlock();
        readLock.unlock();
        }
    }
    
    
    public static void main(String[] args) {
        ReentrantReadWriteLockDemo demo=new ReentrantReadWriteLockDemo();
        demo.get();
    }
}

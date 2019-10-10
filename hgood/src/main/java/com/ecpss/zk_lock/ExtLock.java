package com.ecpss.zk_lock;

/**
 * @version 1.00
 * @date 2019/10/9
 */
public interface ExtLock {

    //ExtLock基于zk实现分布式锁
    public void  getLock();

    //释放锁
    public void unLock();
}

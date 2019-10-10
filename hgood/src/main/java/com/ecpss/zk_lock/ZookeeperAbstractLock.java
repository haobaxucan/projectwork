package com.ecpss.zk_lock;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;

/**
 * @version 1.00
 * @date 2019/10/9
 */
@Slf4j
public abstract class ZookeeperAbstractLock implements ExtLock {
    //    启动一个zk
    private static final String CONNECTION = "localhost:2181";
    protected String lockPath = "/lockPath";
    //    操作zk的管道
    protected static ZkClient zkClient = new ZkClient(CONNECTION);
//    获得锁
    @Override
    public void getLock() {
        if (tryLock()) {
            System.out.println("#####成功获取锁");
        } else {
            //进行等待
            waitLock();
        }
    }
//    释放锁
    @Override
    public void unLock() {
        if(zkClient.exists(lockPath)){
            zkClient.delete(lockPath);
        }
    }

    //创建失败 进行等待
    abstract void waitLock();


    abstract boolean tryLock();
}

package com.ecpss.zk_lock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * @version 1.00
 * @date 2019/10/9
 */
public class ZkDistrbuteLock extends ZookeeperAbstractLock {
    private static CountDownLatch countDownLatch = null;

    // 尝试在zk服务器 创建一个临时节点，（很多线程在这里创建）
//    其它线程先登--》创建了产生了异常
    @Override
    boolean tryLock() {
        try {
            zkClient.createEphemeral(lockPath);
//            System.out.println("#########获取锁######");
            return true;
        } catch (Exception e) {
            // 如果失败 直接catch
            return false;
        }
    }

    // 当前节点path 被占用
//    对当前分布式节点path 注册一个监听器，进行监听
//    产生联动效果，所有的线程--》lock()方法上锁
    @Override
    void waitLock() {
//        客户端有自己监听器
        IZkDataListener iZkDataListener = new IZkDataListener() {

            // 节点被删除
            public void handleDataDeleted(String arg0) throws Exception {
//                联动，再次争抢上锁方法
                if (countDownLatch != null) {
                    countDownLatch.countDown(); // 计数器为0的情况，await 后面的继续执行
//                    再次争抢锁
                    getLock();
                }

            }

            // 节点数据发送修改
            public void handleDataChange(String arg0, Object arg1) throws Exception {

            }
        };

//        刚刚创建的监听器注册到path 上面
        zkClient.subscribeDataChanges(lockPath, iZkDataListener);
//        判断当前节点是否存在
        if (zkClient.exists(lockPath)) {  //如果 检查出 已经被创建了 就new 然后进行等待
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.wait(); //等待时候 就不往下走了   当为0 时候 后面的继续执行
            } catch (Exception e) {

            }
        }
        //后面代码继续执行
        //为了不影响程序的执行 建议删除该事件监听 监听完了就删除掉
        zkClient.unsubscribeDataChanges(lockPath, iZkDataListener);

    }


}

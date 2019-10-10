package com.java_jiji.thread;

/**
 * @version 1.00
 * @date 2019/10/10
 */
public class ClassRunnable implements Runnable{
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println("-------");
    }

    public static void main(String[] args) {

        new Thread(new ClassRunnable()).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}

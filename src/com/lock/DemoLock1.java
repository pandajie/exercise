package com.lock;

/**
 * 初始化一个容器，t1线程往容器中加数据，t2线程监控t1线程，如果容器大小到达5，则停止t2线程。
 *
 * @author ZHAOJIE644
 * @date 2019-11-12
 * @since 1.0.0
 */
public class DemoLock1 {

    public static void main(String[] args) {
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                synchronized ("A") {
                    System.out.println("线程1持有了A锁，等待B锁");
                    try {
                        "A".wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                //
                synchronized ("A") {
                    System.out.println("线程2同时持有了A锁和B锁");
                    "A".notify();
                }
            }
        };


        Thread t1 = new Thread(runnable1);

        Thread t2 = new Thread(runnable2);

        t1.start();
        t2.start();

    }
}

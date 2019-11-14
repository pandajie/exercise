package com.lock;

import java.util.concurrent.TimeUnit;

/**
 * TODO: 请添加描述
 *
 * @author ZHAOJIE644
 * @date 2019-11-12
 * @since 1.0.0
 */
public class DemoLock2 {

    public static void main(String[] args) {
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                    synchronized ("A") {
                        System.out.println("线程1持有了A锁，等待B锁");
                        try {
                            TimeUnit.MILLISECONDS.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            "A".wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized ("B") {
                            System.out.println("线程1同时持有了A锁和B锁");
                        }
                    }


            }
        };


        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                    synchronized ("B") {
                        System.out.println("线程2持有了B锁，等待A锁");

                        synchronized ("A") {
                            System.out.println("线程2同时持有了A锁和B锁");
                            "A".notifyAll();
                        }

                    }


            }
        };


        Thread t1 = new Thread(runnable1);

        Thread t2 = new Thread(runnable2);

        t1.start();
        t2.start();

    }
}

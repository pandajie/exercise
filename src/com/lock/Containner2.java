package com.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * TODO: 请添加描述
 *
 * @author ZHAOJIE644
 * @date 2019-11-13
 * @since 1.0.0
 */
public class Containner2 {


    List<String> contain = new ArrayList<String>();

    CountDownLatch latch = new CountDownLatch(1);

    public void add(){
        System.out.println("容器大小为"+(contain.size()+1));
        contain.add(""+contain.size()+1);
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    Thread t1 = new Thread(){
        @Override
        public void run() {
            while(true) {
                    add();
                    if(contain.size() == 5){
                        latch.countDown();
                    }
                }
            }
        };


    Thread t2 = new Thread(){
        @Override
        public void run(){
                if (contain.size() != 5) {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println("t2结束!");
            }
    };

    public static void main(String[] args) {
        Containner1 con = new Containner1();
        con.t2.start();
        con.t1.start();
    }
}

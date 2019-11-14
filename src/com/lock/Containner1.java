package com.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * TODO: 请添加描述
 *
 * @author ZHAOJIE644
 * @date 2019-11-12
 * @since 1.0.0
 */
public class Containner1 {

    List<String> contain = new ArrayList<String>();

    Object lock  = new Object();

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
                synchronized (lock) {
                    add();
                    if(contain.size() == 5){
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    };


    Thread t2 = new Thread(){
        @Override
        public void run(){
                synchronized (lock) {
                    //
                    if (contain.size() != 5) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    lock.notify();
                    System.out.println("t2结束!");
                }
            }
    };

    public static void main(String[] args) {
        Containner1 con = new Containner1();
        con.t2.start();
        con.t1.start();
    }
}

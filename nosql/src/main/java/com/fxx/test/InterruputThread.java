package com.fxx.test;

import java.util.concurrent.TimeUnit;

public class InterruputThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(){
            @Override
            public void run(){
                while(true) {
                    if (this.isInterrupted()) {
                        System.out.println("线程中断");
                        break;
                    }
                    System.out.println("循环中");
                }
                System.out.println("已跳出循环,线程中断!");

            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(6);
        t1.interrupt();
        System.out.println("执行中断");

        /**
         * 输出结果(无限执行):
         未被中断
         未被中断
         未被中断
         ......
         */
    }
}

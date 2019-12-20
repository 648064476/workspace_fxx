package com.fxx.guava.ListenableFuture;

import com.google.common.util.concurrent.*;
import org.springframework.lang.Nullable;

import java.util.concurrent.ExecutionException;

import static java.util.concurrent.Executors.*;

/**
 * 回调方法ListenableFuture
 * @author gantingting
 */
public class ListenableFutureDemo {
    public static void main (String[] args) {
        //将ExecutorService装饰成ListeningExecutorService
        ListeningExecutorService service = MoreExecutors.listeningDecorator(
                newCachedThreadPool());

        //通过异步的方式计算返回结果
        ListenableFuture<String> future = service.submit(() -> {
            System.out.println("call execute..");
            return "task success!";
        });

        //回调方法1
        //有两种方法可以执行此Future并执行Future完成之后的回调函数
        future.addListener(() -> {  //该方法会在多线程运算完的时候,指定的Runnable参数传入的对象会被指定的Executor执行
            try {
                System.out.println("result: " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }, service);


        //回调方法2
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println("callback result: "+result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        },service);
    }
}

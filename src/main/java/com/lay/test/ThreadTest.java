package com.lay.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Dragon code!
 * @create 2022-12-18 22:04
 */
public class ThreadTest {
    public static void main(String[] args) {

        System.out.println("main方法start");
        Thread01 thread01 = new Thread01();
        thread01.start();//启动线程
        Thread02 thread02 = new Thread02();
        new Thread(thread02).start();

        //该类也是extend于Thread类的，所以也可以new Thread()来运行
        FutureTask<String> futureTask = new FutureTask<String>(new Thread03());
        new Thread(futureTask).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //怎么拿到计算结果呢？
        while(true){
            try {
                String result = futureTask.get();
                System.out.println(result);
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main方法结束了");

    }
    public static class Thread01 extends Thread{
        @Override
        public void run() {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果:"+i);
        }
    }

    public static class Thread02 implements Runnable{
        @Override
        public void run() {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 3;
            System.out.println("运行结果:"+i);
        }
    }

    public static class Thread03 implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 4;
            System.out.println("运行结果:"+i);
            return "callable接口线程执行结束拿到结果";
        }
    }
}

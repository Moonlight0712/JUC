package com.lay.test;

import java.sql.SQLOutput;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Dragon code!
 * @create 2022-12-06 17:15
 */

public class threadActiveCount {
    public static void main(String[] args) {
        //打印当前线程组
        Thread.currentThread().getThreadGroup().list();
        System.out.println(".............");
        System.out.println("当前运行的线程数量:" + Thread.activeCount());


        //TimeUnit的使用，该类是concurrent包下面的一个类，提供了更完美的可读性。用来替代我们的Thread.sleep
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("停顿三秒后。。。");
        }
        //实际是runnable的匿名内部类对象来调用方法，并不是直接实例化我们的Runnable接口，接口是不能实例化的
        new Thread(() -> System.out.println("开启第一个线程"), "线程一").start();

        //关于Runnable接口和Callable接口的区别：
        /*
        Callable接口有返回值（不是void）并且可以抛出异常
         */
        new Thread(new FutureTask<Integer>(() -> {
            try {
                System.out.println("实现callable接口");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 666;
        })).start();

    }
}

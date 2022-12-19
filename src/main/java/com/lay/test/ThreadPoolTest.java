package com.lay.test;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author Dragon code!
 * @create 2022-12-18 22:31
 */
public class ThreadPoolTest {
    //保证当前系统只有一个或两个线程池（数量少，核心业务和非核心业务），每个异步任务直接交给线程池来执行

    public static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        FutureTask<String> futureTask = new FutureTask<String>(()-> "FutureTask线程执行完毕"+ UUID.randomUUID());
        //使用submit来提交任务,可以获取到任务的返回值
        //注意，直接向构造函数中传callable对象就行，不用传FutureTask对象！
        Future<?> submit = pool.submit(()-> "FutureTask线程执行完毕："+ UUID.randomUUID());
        try {

            System.out.println(submit.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            pool.shutdownNow();
        }
        //使用execute就是不能获取到任务的返回值
//        pool.execute(new Thread(futureTask));

        //利用线程池来使用线程
        /**
         * 1.直接使用我们的Executors工具类来创建线程
         * 2.原生创建线程池的方式：new ThreadPoolExecutors
         */

        //自定义线程池来创建线程,构造函数会用到线程池的七大参数
        /**
         * 1.corePoolSize：核心线程数，线程池创建完成后就准备就绪的线程数，等待接受异步任务去执行。
         * 2.maximumPoolSize：线程池的最大线程数量。控制资源
         * 3.keepAliveTime：存活时间，只要线程空闲时间大于我们指定的时间就会释放空闲的线程（最大线程数量减去核心线程数），注意核心线程是不释放的。
         * 4.unit：时间单位
         * 5.BlockQueue<Runnable> workQueue：阻塞队列,如果任务很多，就会将目前多地任务放在阻塞队列中，只要有线程空闲就会去队列中取出新的任务继续执行。
         * 6.threadFactory：线程的创建工厂。
         * 7.handler：当我们的队列满了以后，存不了更多的线程任务，按照指定的拒绝策略拒绝执行任务。
         *
         *
         * 工作顺序：
         * 1.线程池创建好准备好core数量的线程来等待接受任务
         * 2.core满了，来了很多任务就会放到阻塞队列中
         * 3.阻塞队列满了就会开启新的线程，最大只能开启到max线程数量
         * 4.max满了，就用拒绝策略来拒绝多余的线程
         * 5.max满了后max执行好了，max-core数量的线程就会在指定时间后释放，最终保持到core数量
         * 6.new LinkedBlockingQueue<Runnable> 默认是Integer的最大值。可能导致内存不够,所以自己指定为10w个
         *
         * 一个线程池core7 max20 queue50 并发100进来怎么分配？
         * 首先会立即执行7个，然后50个进入阻塞队列，队列满了后再开13个线程到20，此时20加50个已经安排上了剩下30个。
         * 剩下30个使用拒绝策略来执行，一般就是丢弃，如果不想丢弃就可以换其他的拒绝策略来执行
         *
         */
        //线程数量在5-200之间
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
                200,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());


    }
}

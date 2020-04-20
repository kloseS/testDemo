package com.envcloud.redisson.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class RedisTest {

    private static Integer inventory = 1001;

    private static final Integer num = 1000;

    private static LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();

    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(inventory, inventory, 10, TimeUnit.SECONDS, linkedBlockingQueue);
        CountDownLatch countDownLatch = new CountDownLatch(num);
        long start = System.currentTimeMillis();
        for (int i = 0; i <= num; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    reentrantLock.lock();
                        inventory--;
                    reentrantLock.unlock();
                    System.out.println("线程执行：" + Thread.currentThread().getName());
                    countDownLatch.countDown();
                }
            });
        }
        threadPoolExecutor.shutdown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(num + "个线程，耗时" + (end - start) + "秒,剩余库存为" + inventory);
    }
}

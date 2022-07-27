package com.zaqbest.study.misc.my;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class ConCurrentDemo {
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int N = 100;
        CountDownLatch countDownLatch = new CountDownLatch(N);
        List<Future> futureList = new LinkedList<>();
        for (int i = 0; i < N; i++) {

            futureList.add(threadPool.submit(new MyThread(countDownLatch)));
        }

        countDownLatch.await();

        System.out.println("all finished");
        for (int i = 0; i < N; i++){
            System.out.println((i+1) + ":" + futureList.get(i).get());
        }

        threadPool.shutdownNow();
    }

}

 class MyThread implements Callable<String> {
    CountDownLatch countDownLatch;

    MyThread(CountDownLatch countDownLatch){
        this.countDownLatch  = countDownLatch;
    }

     @Override
     public String call() throws Exception {

         String result = RandomUtil.randomString(10);
         System.out.println(Thread.currentThread().getName() + ": " + result);

         ThreadUtil.safeSleep(500);

         countDownLatch.countDown();

         return result;
     }
 }

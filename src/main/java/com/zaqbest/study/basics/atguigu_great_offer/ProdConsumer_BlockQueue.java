package com.zaqbest.study.basics.atguigu_great_offer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 *
 */
class MyResource{
    private volatile boolean FLAG = true; //默认开启，进行生产和消费
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        while (FLAG){
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);

            if (retValue){
                System.out.println(Thread.currentThread().getName() + "\t 插入队列"+data+"成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列"+data+"失败");
            }

            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println(Thread.currentThread().getName() + "\t 大老板叫停了，生产结束");
    }

    public void myConsumer() throws Exception{
        String result = null;
        while (FLAG){
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "超过2秒没有取到数据，队列退出");
                System.out.println();
                System.out.println();
                return;

            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");
        }
    }

    public void stop() {
        FLAG = false;
    }
}
public class ProdConsumer_BlockQueue {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
            try{
                myResource.myProd();
            } catch (Exception e){
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
            try{
                myResource.myConsumer();
            } catch (Exception e){
                e.printStackTrace();
            }
        },"Consumer").start();


        try{
            TimeUnit.SECONDS.sleep(5);
        } catch(Exception e) {e.printStackTrace();}

        System.out.println();
        System.out.println();
        System.out.println("5秒钟时间到，大老板main线程叫停，活动结束");
        myResource.stop();



    }



}

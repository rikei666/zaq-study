package com.zaqbest.study.jvm.concurrency;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试一个线程OOM, 另一个线程仍然和可以正常运行
 *
 * -Xms16m -Xmx32m
 */
public class ThreadOOMTest {
    public static void main(String[] args) {
        new Thread(() -> {
            List<byte[]> list= new ArrayList<>();
            while(true){
                System.out.println(new Date().toString()+Thread.currentThread()+"==");
                byte[] b = new byte[1024*1024*1];
                list.add(b);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while(true){
                System.out.println(new Date().toString()+Thread.currentThread()+"==");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

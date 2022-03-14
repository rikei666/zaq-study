package com.zaqbest.study.anquan;

import cn.hutool.core.net.NetUtil;
import com.zaqbest.study.anquan.ext.MySyncFinisher;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 端口扫描
 */
public class PortScanner {
    public static void main(String[] args) {
        String ip = "192.168.1.50";
        int portFrom = 1;
        int portTo = 10000;

        MySyncFinisher mySyncFinisher = new MySyncFinisher(100);
        List<Integer> ports = Collections.synchronizedList(new ArrayList<>());
        for (int port = portFrom; port <= portTo; port++){
            mySyncFinisher.addWorker(new MyWorker(ip, port, ports));
        }
        mySyncFinisher.start(true);

        System.out.println("completed");
        System.out.println(ports.size()+":"+ports);

    }

}

class MyWorker implements Runnable{

    private String ip;
    private int port;
    private List<Integer> result;


    public MyWorker(String ip, int port, List<Integer> result) {
        this.ip = ip;
        this.port = port;
        this.result = result;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " try port " + port);
        boolean isOpen = NetUtil.isOpen(new InetSocketAddress(ip, port), 200);
        if (isOpen) result.add(port);
    }
}

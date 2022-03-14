package com.zaqbest.study.anquan;

import cn.hutool.core.net.Ipv4Util;
import cn.hutool.core.net.NetUtil;
import com.zaqbest.study.anquan.ext.MySyncFinisher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 主要功能
 * 主机发现，可以扫描范围内的主机是否存活
 *
 */
public class HostScanner {
    public static void main(String[] args) {

        MySyncFinisher syncFinisher = new MySyncFinisher(100);
        List<String> ips = Ipv4Util.list("192.168.1.1", "192.168.1.255");
        List<String> results = Collections.synchronizedList(new ArrayList<>());

        for(String ip: ips){
            syncFinisher.addWorker(new MyWorker(ip, results));
        }

        syncFinisher.start(true);

        System.out.println("completed");
        Collections.sort(results);
        System.out.println(results.size()+":"+results);
    }

    static class MyWorker implements Runnable{
        private String ip;
        private List<String> result;

        public MyWorker(String ip, List<String> result) {
            this.ip = ip;
            this.result = result;
        }

        @Override
        public void run() {
            boolean isOk = NetUtil.ping(ip, 5000);
            if (isOk) result.add(ip);
        }
    }
}

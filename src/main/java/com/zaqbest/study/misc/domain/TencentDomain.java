package com.zaqbest.study.misc.domain;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.domain.v20180808.DomainClient;
import com.tencentcloudapi.domain.v20180808.models.CheckDomainRequest;
import com.tencentcloudapi.domain.v20180808.models.CheckDomainResponse;
import com.zaqbest.comm.utils.PropUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TencentDomain {

    private static List<String> domainList = Arrays.asList("art" ,"auto" ,"biz" ,"cc" ,"center" ,"chat" ,"city" ,"club" ,"cn" ,"co" ,"com" ,"company" ,"cool" ,"design" ,"email" ,"fit" ,"fun" ,"fund" ,"gold" ,"group" ,"guru" ,"host" ,"icu" ,"info" ,"ink" ,"kim" ,"life" ,"link" ,"live" ,"love" ,"ltd" ,"luxe" ,"mobi" ,"net" ,"online" ,"pet" ,"plus" ,"press" ,"pro" ,"pub" ,"red" ,"ren" ,"run" ,"shop" ,"show" ,"site" ,"social" ,"space" ,"store" ,"team" ,"tech" ,"today" ,"top" ,"tv" ,"video" ,"vip" ,"wang" ,"website" ,"wiki" ,"work" ,"world" ,"xin" ,"xyz" ,"yoga" ,"zone");

    private static List<String> getCombination(char[] str, int size){
        List<String> result = new ArrayList<>();
        char[] path = new char[size];
        process(str, size, 0, path, result);
        return result;
    }

    private static void  process(char[] str, int size, int cur, char[] path, List<String> result){
        if (cur == size){
            result.add(String.valueOf(path));
        } else {
            for (int i =0; i < str.length; i++){
                path[cur] = str[i];
                process(str, size, cur+1, path, result);
            }
        }
    }

    public static void main(String [] args) throws InterruptedException {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        String digit = "0123456789";
        String str = alpha + digit;

        List<String> result = new ArrayList<>();
        result.addAll(getCombination(str.toCharArray(), 1));
//        result.addAll(getCombination(str.toCharArray(), 2));
//        result.addAll(getCombination(str.toCharArray(), 3));
//        result.addAll(getCombination(str.toCharArray(), 4));

        result = result.stream().filter(s -> s.equals(StrUtil.reverse(s))).collect(Collectors.toList());

//        System.out.println(JSON.toJSONString(result));
//        System.out.println(result.size());

        ThreadPoolExecutor threadPool  = new ThreadPoolExecutor(3,
                3,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
        int totalTask = 0;
        for (String prefix: result){
            for (String postfix: domainList){
                totalTask++;
                threadPool.submit(new Worker(prefix+"."+postfix));
            }
        }
        System.out.println("总任务数："+totalTask);

        while (threadPool.getCompletedTaskCount() != totalTask){
            Thread.yield();
            TimeUnit.SECONDS.sleep(3);
        }

        threadPool.shutdownNow();
    }

    @Slf4j
    static class Worker implements Runnable{
        String domain;

        public Worker(String domain){
            this.domain = domain;
        }

        @Override
        public void run() {
            boolean result = checkDomain(domain);
            log.info("{} - {}", domain, result);
            if (result){
                log.info("##################################  {} ##################################", domain);
            }
        }
    }
    private static boolean checkDomain(String domain) {
        // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
        //final Credential cred = new Credential(PropUtil.getValue("tencentSecretId"), PropUtil.getValue("tencentSecretKey"));
        Credential cred = new Credential("AKIDzpJWifHxOZpoHnjWhGsEqCHH1VpvbTu5", "ECuWnohJeYN2dqngIgofFgwxpRn50fTu");
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("domain.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            DomainClient client = new DomainClient(cred, "", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            CheckDomainRequest req = new CheckDomainRequest();

            req.setDomainName(domain);
            // 返回的resp是一个CheckDomainResponse的实例，与请求对象对应
            CheckDomainResponse resp = client.CheckDomain(req);
            // 输出json格式的字符串回包
            if (resp.getAvailable()){
                //System.out.println(CheckDomainResponse.toJsonString(resp));
                return true;
            }

            return false;
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

        return false;
    }
}

package com.zaqbest.study.redis;

import cn.hutool.json.JSONUtil;
import com.zaqbest.comm.service.RedisService;
import com.zaqbest.study.BaseTest;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class RedisTest extends BaseTest {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedissonClient redissonClient;

    static class MyClass{
        public Integer a;
        public String s;
    }

    @Test
    public void setCache(){
        redisService.set("aa", 86400L);

        MyClass myClass = new MyClass();
        myClass.a = 1;
        myClass.s = "hello";

        redisService.set("bb", myClass, 86400L);
        MyClass myClass2 = (MyClass) redisService.get("bb");
        System.out.println(JSONUtil.toJsonPrettyStr(myClass2));
    }

    /**
     * 默认保持30s, 当逝去1/3时，renew超时时间
     *
     * @throws InterruptedException
     */
    @Test
    public void readWriteLock() throws InterruptedException {
        RLock lock = redissonClient.getLock("mylock");
        lock.tryLock(3, TimeUnit.SECONDS);

        lock.unlock();
//        while (true){
//            TimeUnit.SECONDS.sleep(10);
//        }
    }
}

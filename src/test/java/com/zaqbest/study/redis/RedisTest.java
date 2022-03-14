package com.zaqbest.study.redis;

import com.zaqbest.redis.RedisClient;
import com.zaqbest.study.BaseTest;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class RedisTest extends BaseTest {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void setCache(){
        redisClient.set("aa", "a1", "mypre", 86400L);
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

package com.zaqbest.study.middleware.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String QUEUENAME = "domain";

    @Bean
    public Queue domainQueue() {
        return new Queue(QUEUENAME);
    }
}

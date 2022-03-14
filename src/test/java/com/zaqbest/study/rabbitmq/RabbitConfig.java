package com.zaqbest.study.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String QUEUENAME = "chatbot";

    @Bean
    public Queue helloQueue() {
        return new Queue(QUEUENAME);
    }
}

package com.zaqbest.study.middleware.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {

    public static final String QUEUENAME = "domain";

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String message) {
        String context = message;
        System.out.println("Sender:" + context);
        this.rabbitTemplate.convertAndSend(QUEUENAME, context);
    }
}
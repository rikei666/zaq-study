package com.zaqbest.study.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {

    public static final String QUEUENAME = "chatbot";

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String message) {
        String context = message + new Date();
        System.out.println("Sender:" + context);
        this.rabbitTemplate.convertAndSend(QUEUENAME, context);
    }
}
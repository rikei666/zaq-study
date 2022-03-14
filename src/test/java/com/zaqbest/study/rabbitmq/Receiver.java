package com.zaqbest.study.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "chatbot")
public class Receiver {

    @RabbitHandler
    public void process(String s) {
        System.out.println("Receiver:" + s);
    }
}

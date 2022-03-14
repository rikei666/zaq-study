package com.zaqbest.study.rabbitmq;

import com.zaqbest.study.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SenderTest extends BaseTest {

    @Autowired
    private Sender sender;

    @Before
    public void before() throws Exception {
        System.out.println("Start sending message...");
    }

    @After
    public void after() throws Exception {
        System.out.println("Finish sending message！");
    }

    /**
     * Method: send()
     */
    @Test
    public void testSend() throws Exception {
        for (int i = 0; i < 10; i++) {
            sender.send("hello No."+ i);
        }
    }
}
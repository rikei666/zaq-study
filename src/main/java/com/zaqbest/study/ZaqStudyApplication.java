package com.zaqbest.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.zaqbest"})
public class ZaqStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZaqStudyApplication.class, args);
    }

}

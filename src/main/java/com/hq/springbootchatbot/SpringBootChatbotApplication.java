package com.hq.springbootchatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootChatbotApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-dev");
        SpringApplication.run(SpringBootChatbotApplication.class, args);
    }

}

package com.project_management.shoppingweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.project_management.shoppingweb.controller.*")

public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}


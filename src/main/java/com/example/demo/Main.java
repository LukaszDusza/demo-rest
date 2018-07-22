package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class Main extends SpringBootServletInitializer {

        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(Main.class);
        }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }




}

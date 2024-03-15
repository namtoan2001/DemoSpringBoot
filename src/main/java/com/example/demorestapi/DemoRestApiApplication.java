package com.example.demorestapi;

import com.example.demorestapi.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoRestApiApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoRestApiApplication.class);
        application.addListeners(new SwaggerConfig());
        application.run(args);
    }
}

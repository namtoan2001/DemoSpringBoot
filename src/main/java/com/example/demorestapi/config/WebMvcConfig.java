package com.example.demorestapi.config;

import com.example.demorestapi.interceptors.LogCountInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final LogCountInterceptors requestCountInterceptor;

    public WebMvcConfig(LogCountInterceptors requestCountInterceptor) {
        this.requestCountInterceptor = requestCountInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestCountInterceptor).addPathPatterns("/**");
    }
}


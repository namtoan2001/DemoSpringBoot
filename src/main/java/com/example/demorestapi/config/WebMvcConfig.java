package com.example.demorestapi.config;

import com.example.demorestapi.interceptors.RequestCountInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final RequestCountInterceptors requestCountInterceptor;

    public WebMvcConfig(RequestCountInterceptors requestCountInterceptor) {
        this.requestCountInterceptor = requestCountInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestCountInterceptor).addPathPatterns("/**");
    }
}


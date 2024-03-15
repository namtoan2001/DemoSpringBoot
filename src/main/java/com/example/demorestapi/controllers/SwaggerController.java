package com.example.demorestapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {

    @RequestMapping("/DemoRestAPI")
    public String getRedirectUrl() {
        return "redirect:swagger-ui/";
    }
}
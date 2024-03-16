package com.example.demorestapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = {"https://demospringboot-production-7e99.up.railway.app","http://demospringboot-production-7e99.up.railway.app"}, allowCredentials = "true")
public class SwaggerController {

    @RequestMapping("/DemoRestAPI")
    public String getRedirectUrl() {
        return "redirect:swagger-ui/";
    }
}
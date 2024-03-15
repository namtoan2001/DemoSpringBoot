package com.example.demorestapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {
    @GetMapping("/endpoint")
    public void myEndpoint() {
        // Xử lý request và trả về kết quả
    }
}

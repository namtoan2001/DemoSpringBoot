package com.example.demorestapi.controllers;

import com.example.demorestapi.services.LogCountService;
import com.example.demorestapi.viewModels.LogCountVM;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://demospringboot-production-7e99.up.railway.app", "http://demospringboot-production-7e99.up.railway.app"}, allowCredentials = "true")
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/LogCount")
public class LogCountController {
    private final LogCountService logCountService;

    public LogCountController(LogCountService logCountService) {
        this.logCountService = logCountService;
    }
    @GetMapping("/GetLogCount")
    public List<LogCountVM> getLogCount() throws Exception {
        var result = logCountService.GetLogCount().stream().toList();
        if(result.isEmpty()) {
            throw new Exception("Khong tim thay du lieu nao!");
        }
        return result;
    }
}

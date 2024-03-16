package com.example.demorestapi.controllers;

import com.example.demorestapi.services.SendMailService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = {"https://demospringboot-production-7e99.up.railway.app","http://demospringboot-production-7e99.up.railway.app"}, allowCredentials = "true")
@RequestMapping("/api/sendmail")
public class SendMailController {
    private final SendMailService sendMailService;

    public SendMailController(SendMailService sendMailService) {
        this.sendMailService = sendMailService;
    }
    @PostMapping("/send")
    public String sendMail() throws MessagingException, IOException {
        sendMailService.sendMail();
        return "Mail sent successfully!";
    }
}

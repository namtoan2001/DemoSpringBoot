package com.example.demorestapi.interfaces;

import jakarta.mail.MessagingException;
import java.io.IOException;

public interface ISendMailService {
    void sendMail() throws MessagingException, IOException;
}

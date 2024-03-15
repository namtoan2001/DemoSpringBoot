package com.example.demorestapi.services;

import com.example.demorestapi.interfaces.IJwtService;
import com.example.demorestapi.interfaces.ISendMailService;
import com.example.demorestapi.models.User;
import com.example.demorestapi.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class SendMailService implements ISendMailService {
    private final JavaMailSender javaMailSender;
    private final IJwtService jwtService;
    private final HttpServletRequest httpServletRequest;
    private final UserRepository userRepository;

    public SendMailService(JavaMailSender javaMailSender, IJwtService jwtService, HttpServletRequest httpServletRequest, UserRepository userRepository) {
        this.javaMailSender = javaMailSender;
        this.jwtService = jwtService;
        this.httpServletRequest = httpServletRequest;
        this.userRepository = userRepository;
    }
    public Claims getJwtClaims() throws JwtException {
        String authHeader = httpServletRequest.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer ".length()).trim();
            return jwtService.decodeJwt(token);
        }
        return null;
    }
    @Override
    @Transactional
    public void sendMail() throws MessagingException, IOException {
        Claims claims = getJwtClaims();
        String to = Optional.ofNullable(userRepository.findById(Integer.parseInt(claims.getSubject())))
                .map(User::getEmail)
                .orElse(null);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
        String subject = "Đây là mail Test";
        String text = readHtmlFile();
        assert to != null;
        text = text.replace("{TO}", to);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        javaMailSender.send(message);
    }

    private String readHtmlFile() throws IOException {
        String filePath = "templates/MailTemplate.html";
        ClassPathResource resource = new ClassPathResource(filePath);
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return new String(bytes, StandardCharsets.UTF_8);
    }
}

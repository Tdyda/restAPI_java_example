package com.example.restapi_java.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender mailSender;

    public void sendRoleAssignedMail(String to, Map<String, Object> payload) {
        String id = (String) payload.get("id");
        String roleName = (String) payload.get("roleName");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tdyda@doublecodestudio.pl");
        message.setTo(to);
        message.setSubject("Nowa rola przypisana w systemie");
        message.setText("Użytkownik o ID: " + id + " dostał nową rolę: " + roleName);
        try {
            log.info("About to send mail to {}", to);
            mailSender.send(message);
            log.info("Mail sent successfully to {}", to);
        } catch (Exception e) {
            log.error("Error sending mail to {}: {}", to, e.getMessage(), e);
        }
    }

    public void sendAccountVerificationMail(String to,  Map<String, Object> payload) {
        String verificationToken = (String) payload.get("verificationToken");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tdyda@doublecodestudio.pl");
        message.setTo(to);
        message.setSubject("Account confirmation");
        message.setText("Click this link to activate your account: " + verificationToken);

        try {
            log.info("About to send mail to {}", to);
            mailSender.send(message);
            log.info("Mail sent successfully to {}", to);
        } catch (Exception e) {
            log.error("Error sending mail to {}: {}", to, e.getMessage(), e);
        }
    }

    public void sendUserNotificationMail(String to) {

    }
}
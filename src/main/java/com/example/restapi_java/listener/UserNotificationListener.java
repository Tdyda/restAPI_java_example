package com.example.restapi_java.listener;

import com.example.restapi_java.dto.events.UserNotificationEvent;
import com.example.restapi_java.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserNotificationListener {
    private final MailService mailService;

    @RabbitListener(queues = "${app.rabbitmq.user-notification-queue.name}")
    public void handleUserNotificationEvent(UserNotificationEvent event) {
        log.info("Received user notification event" + event);
        switch (event.getType()) {
            case "SIGN-UP" -> mailService.sendAccountVerificationMail(event.getEmail(), event.getPayload());
            case "ROLE-ASSIGNMENT" -> mailService.sendRoleAssignedMail(event.getEmail(), event.getPayload());
        }
    }
}

package com.example.restapi_java.publisher;

import com.example.restapi_java.dto.events.UserNotificationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserNotificationEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.user-notification-queue.routing-key}")
    private String routingKey;

    public void publish(UserNotificationEvent event) {
        log.info("send notification");
        rabbitTemplate.convertAndSend(exchange, routingKey, event);
    }
}
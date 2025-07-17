package com.example.restapi_java.dto.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserNotificationEvent {
    private String email;
    private String type;
    private Map<String, Object> payload;
}

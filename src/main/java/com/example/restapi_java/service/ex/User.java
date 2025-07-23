package com.example.restapi_java.service.ex;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class User {
    private String name;
    private int age;
    private String role; // "ADMIN", "USER", "GUEST"
    private boolean active;
}


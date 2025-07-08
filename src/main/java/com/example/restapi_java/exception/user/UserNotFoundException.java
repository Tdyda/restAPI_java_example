package com.example.restapi_java.exception.user;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends UserException {
    public UserNotFoundException(String email) {
        super(HttpStatus.NOT_FOUND, "User not found: " + email);
    }
}

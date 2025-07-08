package com.example.restapi_java.exception.user;

import org.springframework.http.HttpStatus;

public class EmailAlreadyInUseException extends UserException {
    public EmailAlreadyInUseException(String email) {
        super(HttpStatus.BAD_REQUEST, "Email already exists: " + email);
    }
}

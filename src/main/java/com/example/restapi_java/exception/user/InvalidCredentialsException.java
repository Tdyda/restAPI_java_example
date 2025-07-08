package com.example.restapi_java.exception.user;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends UserException {
    public InvalidCredentialsException() {

        super(HttpStatus.UNAUTHORIZED, "Invalid credentials!");
    }
}
package com.example.restapi_java.exception.user;

import com.example.restapi_java.exception.ApiException;
import org.springframework.http.HttpStatus;

public class UserException extends ApiException {
    public UserException(HttpStatus status, String message) {
        super(status, message);
    }
}
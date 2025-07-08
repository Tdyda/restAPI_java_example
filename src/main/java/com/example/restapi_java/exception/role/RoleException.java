package com.example.restapi_java.exception.role;

import com.example.restapi_java.exception.ApiException;
import org.springframework.http.HttpStatus;

public class RoleException extends ApiException {
    public RoleException(HttpStatus status, String message) {
        super(status, message);
    }
}

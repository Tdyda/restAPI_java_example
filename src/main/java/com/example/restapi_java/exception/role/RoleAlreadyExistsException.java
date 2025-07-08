package com.example.restapi_java.exception.role;

import org.springframework.http.HttpStatus;

public class RoleAlreadyExistsException extends RoleException {
    public RoleAlreadyExistsException(String roleName) {
        super(HttpStatus.BAD_REQUEST, "Role already exists: " + roleName);
    }
}

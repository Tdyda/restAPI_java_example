package com.example.restapi_java.exception.role;

import org.springframework.http.HttpStatus;

public class RoleAlreadyAssignedException extends RoleException {
    public RoleAlreadyAssignedException(String userId) {
        super(HttpStatus.CONFLICT, "Role already assigned to user " + userId);
    }
}

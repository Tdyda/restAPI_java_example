package com.example.restapi_java.exception.role;

import com.example.restapi_java.exception.ApiException;
import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends ApiException {
    public RoleNotFoundException(String roleName) {

        super(HttpStatus.BAD_REQUEST, "Role not found: " + roleName);
    }
}

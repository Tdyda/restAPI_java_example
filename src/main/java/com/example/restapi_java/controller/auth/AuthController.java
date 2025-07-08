package com.example.restapi_java.controller.auth;

import com.example.restapi_java.controller.BaseController;
import com.example.restapi_java.dto.auth.AuthCredentials;
import com.example.restapi_java.dto.auth.AuthResponse;
import com.example.restapi_java.dto.auth.SignUp;
import com.example.restapi_java.dto.roles.AssignRoleRequest;
import com.example.restapi_java.model.User;
import com.example.restapi_java.service.auth.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> createUser(@RequestBody @Valid SignUp request) {
        log.info("Received sign-up request for email: {}", request.getEmail());

        User createdUser = userService.createUser(request);

        log.info("Successfully created user with ID: {}", createdUser.getId());

        return CREATED();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthCredentials request) {
        log.info("Received sign-in request");

        AuthResponse token = userService.signIn(request);

        return OK(token);
    }

    @PostMapping("/assign-role")
    public ResponseEntity<Void> addRoles(@RequestBody @Valid AssignRoleRequest request) {
        log.info("Received role request");

        userService.assignRole(request);

        return OK();
    }
}

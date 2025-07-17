package com.example.restapi_java.service.auth;

import com.example.restapi_java.dto.auth.*;
import com.example.restapi_java.dto.events.UserNotificationEvent;
import com.example.restapi_java.dto.roles.AssignRoleRequest;
import com.example.restapi_java.exception.role.RoleAlreadyAssignedException;
import com.example.restapi_java.exception.role.RoleNotFoundException;
import com.example.restapi_java.exception.user.EmailAlreadyInUseException;
import com.example.restapi_java.exception.user.InvalidCredentialsException;
import com.example.restapi_java.exception.user.UserNotFoundException;
import com.example.restapi_java.model.Role;
import com.example.restapi_java.model.User;
import com.example.restapi_java.publisher.UserNotificationEventPublisher;
import com.example.restapi_java.repository.RoleRepository;
import com.example.restapi_java.repository.UserRepository;
import com.example.restapi_java.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private final UserNotificationEventPublisher userNotificationEventPublisher;

    public User createUser(SignUp request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyInUseException(request.getEmail());
        }

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RoleNotFoundException("ROLE_USER"));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(userRole));

        String verificationToken = UUID.randomUUID().toString();
        Map<String, Object> payload = new HashMap<>();
        payload.put("verificationToken", verificationToken);

        UserNotificationEvent event = new UserNotificationEvent(user.getEmail(), "SIGN-UP", payload);
        userNotificationEventPublisher.publish(event);

        return userRepository.save(user);
    }

    public AuthResponse signIn(AuthCredentials request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(
                String.valueOf(user.getId()),
                user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet())
        );

        return new AuthResponse(token);
    }

    public void assignRole(AssignRoleRequest request) {
        Role role = roleRepository.findByName(request.getName())
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(request.getEmail()));

        if (user.getRoles().contains(role)) {
            throw new RoleAlreadyAssignedException(String.valueOf(user.getId()));
        }

        user.getRoles().add(role);
        userRepository.save(user);

        Map<String, Object> payload = new HashMap<>();
        payload.put("id", user.getId());
        payload.put("roleName", role.getName());

        UserNotificationEvent event =  new UserNotificationEvent(user.getEmail(), "ROLE-ASSIGNMENT", payload);
        userNotificationEventPublisher.publish(event);
    }

    public void unassignRoleFromAllUsers(Role role) {
        List<User> users = userRepository.findAllByRolesContaining(role);

        for (User user : users) {
            user.getRoles().remove(role);
        }

        userRepository.saveAll(users);
    }
}

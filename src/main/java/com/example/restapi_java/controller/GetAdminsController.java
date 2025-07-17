package com.example.restapi_java.controller;

import com.example.restapi_java.dto.UserDTO;
import com.example.restapi_java.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class GetAdminsController extends BaseController {
    private final UserRepository userRepository;

    @GetMapping("/admins")
    public ResponseEntity<List<UserDTO>> GetAdmins() {
        List<UserDTO> users = userRepository.findAll()
                .stream()
                .filter(user -> user.getRoles().stream()
                        .anyMatch(role -> role.getName().equals("ROLE_ADMIN")))
                .map(user -> new UserDTO(user.getId(), user.getEmail(), user.getUsername()))
                .toList();

        return OK(users);
    }
}

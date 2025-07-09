package com.example.restapi_java.config;

import com.example.restapi_java.model.Role;
import com.example.restapi_java.model.User;
import com.example.restapi_java.repository.RoleRepository;
import com.example.restapi_java.repository.UserRepository;
import com.example.restapi_java.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Profile("!test")
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Value("${admin.default.password}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        roleService.init();

        if (!userRepository.existsByEmail("admin@example.com")) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new IllegalStateException("ROLE_ADMIN should exist at this point"));

            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);
        }
    }
}


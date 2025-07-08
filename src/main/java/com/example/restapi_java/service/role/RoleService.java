package com.example.restapi_java.service.role;

import com.example.restapi_java.dto.roles.AssignRoleRequest;
import com.example.restapi_java.exception.role.RoleAlreadyExistsException;
import com.example.restapi_java.model.Role;
import com.example.restapi_java.repository.RoleRepository;
import com.example.restapi_java.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public void init() {
        List<String> roles = List.of("ROLE_ADMIN", "ROLE_USER");

        roles.forEach(name -> {
                    if (roleRepository.findByName(name).isEmpty()) {
                        Role role = new Role();
                        role.setName(name);
                        roleRepository.save(role);
                    } else {
                        throw new RoleAlreadyExistsException(name);
                    }
                }
        );
    }
}

package com.example.restapi_java.service.role;

import com.example.restapi_java.dto.roles.RoleRequest;
import com.example.restapi_java.dto.roles.RoleResponse;
import com.example.restapi_java.dto.roles.UpdateRoleRequest;
import com.example.restapi_java.exception.role.RoleAlreadyExistsException;
import com.example.restapi_java.exception.role.RoleNotFoundException;
import com.example.restapi_java.model.Role;
import com.example.restapi_java.repository.RoleRepository;
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
                    }
                }
        );
    }

    public RoleResponse createRole(RoleRequest request) {
        if (roleRepository.findByName(request.getName()).isEmpty()) {
            Role role = new Role();
            role.setName(request.getName());
            roleRepository.save(role);

            return toDto(role);
        } else {
            throw new RoleAlreadyExistsException(request.getName());
        }
    }

    public Role getRoleEntityByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException(name));
    }

    public RoleResponse getRole(String roleName) {
        Role role = getRoleEntityByName(roleName);

        return toDto(role);
    }

    public RoleResponse updateRole(UpdateRoleRequest request, String name) {
        Role role = getRoleEntityByName(name);
        role.setName(request.getNewRoleName());
        roleRepository.save(role);

        return toDto(role);
    }

    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    private RoleResponse toDto(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

}

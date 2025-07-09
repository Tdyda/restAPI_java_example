package com.example.restapi_java.service.management;

import com.example.restapi_java.model.Role;
import com.example.restapi_java.service.auth.UserService;
import com.example.restapi_java.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleManagementService {
    private final RoleService roleService;
    private final UserService userService;

    public void deleteRoleByName(String roleName) {
        Role role = roleService.getRoleEntityByName(roleName);
        userService.unassignRoleFromAllUsers(role);
        roleService.deleteRole(role);
    }
}

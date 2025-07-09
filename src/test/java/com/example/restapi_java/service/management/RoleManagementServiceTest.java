package com.example.restapi_java.service.management;

import com.example.restapi_java.model.Role;
import com.example.restapi_java.service.auth.UserService;
import com.example.restapi_java.service.role.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleManagementServiceTest {
    @Mock
    private RoleService roleService;

    @Mock
    private UserService userService;

    @InjectMocks
    private RoleManagementService roleManagementService;

    @Test
    void shouldDeleteRoleByName_whenRoleExists() {
        String roleName = "roleName";

        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName(roleName);

        when(roleService.getRoleEntityByName(roleName)).thenReturn(role);

        roleManagementService.deleteRoleByName(roleName);

        verify(roleService).getRoleEntityByName(roleName);
        verify(userService).unassignRoleFromAllUsers(role);
        verify(roleService).deleteRole(role);
    }
}

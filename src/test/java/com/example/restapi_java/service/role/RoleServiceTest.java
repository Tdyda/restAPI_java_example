package com.example.restapi_java.service.role;

import com.example.restapi_java.dto.roles.RoleRequest;
import com.example.restapi_java.dto.roles.RoleResponse;
import com.example.restapi_java.dto.roles.UpdateRoleRequest;
import com.example.restapi_java.exception.role.RoleAlreadyExistsException;
import com.example.restapi_java.exception.role.RoleNotFoundException;
import com.example.restapi_java.model.Role;
import com.example.restapi_java.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    public void shouldCreateNewRole_whenRoleDoesNotExist() {
        RoleRequest roleRequest = new RoleRequest("ROLE_TEST");
        when(roleRepository.findByName("ROLE_TEST")).thenReturn(Optional.empty());

        roleService.createRole(roleRequest);

        ArgumentCaptor<Role> roleCaptor = ArgumentCaptor.forClass(Role.class);
        verify(roleRepository).save(roleCaptor.capture());

        Role savedRole = roleCaptor.getValue();
        assertEquals("ROLE_TEST", savedRole.getName());
    }

    @Test
    public void shouldThrowException_whenRoleAlreadyExists() {
        RoleRequest roleRequest = new RoleRequest("ROLE_TEST");
        when(roleRepository.findByName("ROLE_TEST")).thenReturn(Optional.of(new Role()));

        assertThrows(RoleAlreadyExistsException.class, () -> roleService.createRole(roleRequest));
    }

    @Test
    void shouldReturnRole_whenRoleExists() {
        Role role = new Role();
        UUID roleId = UUID.randomUUID();
        role.setId(roleId);

        role.setName("ROLE_TEST");
        when(roleRepository.findByName("ROLE_TEST")).thenReturn(Optional.of(role));

        var response = roleService.getRole("ROLE_TEST");

        assertEquals(roleId, response.getId());
        assertEquals("ROLE_TEST", response.getName());
    }

    @Test
    void shouldThrowException_whenRoleDoesNotExists() {
        when(roleRepository.findByName("ROLE_TEST")).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> roleService.getRole("ROLE_TEST"));
    }

    @Test
    void shouldUpdateRole_whenRoleExists() {
        Role role = new Role();
        UUID roleId = UUID.randomUUID();
        role.setId(roleId);
        role.setName("ROLE_OLD");

        UpdateRoleRequest updateRoleRequest = new UpdateRoleRequest("ROLE_NEW");

        when(roleRepository.findByName("ROLE_OLD")).thenReturn(Optional.of(role));

        RoleResponse roleResponse = roleService.updateRole(updateRoleRequest, "ROLE_OLD");

        ArgumentCaptor<Role> roleCaptor = ArgumentCaptor.forClass(Role.class);
        verify(roleRepository).save(roleCaptor.capture());

        Role savedRole = roleCaptor.getValue();
        assertEquals(updateRoleRequest.getNewRoleName(), savedRole.getName());
        assertEquals(updateRoleRequest.getNewRoleName(), roleResponse.getName());
        assertEquals(roleId, roleResponse.getId());
    }

    @Test
    void shouldDeleteRole_whenRoleExists() {
        Role role = new Role();
        UUID roleId = UUID.randomUUID();
        role.setId(roleId);
        role.setName("ROLE_TEST");

        roleService.deleteRole(role);
        verify(roleRepository).delete(role);
    }

    @Test
    void shouldSaveOnlyMissingRoles_whenSomeRolesAlreadyExist() {
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.empty());
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(new Role()));

        roleService.init();

        ArgumentCaptor<Role> roleCaptor = ArgumentCaptor.forClass(Role.class);
        verify(roleRepository).save(roleCaptor.capture());

        Role savedRole = roleCaptor.getValue();
        assertEquals("ROLE_ADMIN", savedRole.getName());
        verify(roleRepository, times(1)).save(any(Role.class));

        verify(roleRepository).findByName("ROLE_USER");
        verify(roleRepository, never()).save(argThat(role -> "ROLE_USER".equals(role.getName())));
    }

    @Test
    void shouldInitRoles_whenRolesDoesNotExist() {
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.empty());
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.empty());

        roleService.init();

        ArgumentCaptor<Role> roleCaptor = ArgumentCaptor.forClass(Role.class);
        verify(roleRepository, times(2)).save(roleCaptor.capture());

        List<Role> savedRoles = roleCaptor.getAllValues();
        List<String> savedRoleNames = savedRoles.stream().map(Role::getName).toList();

        assertTrue(savedRoleNames.contains("ROLE_ADMIN"));
        assertTrue(savedRoleNames.contains("ROLE_USER"));
    }
}

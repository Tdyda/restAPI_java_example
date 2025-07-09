package com.example.restapi_java.service.auth;

import com.example.restapi_java.dto.auth.AuthCredentials;
import com.example.restapi_java.dto.auth.AuthResponse;
import com.example.restapi_java.dto.auth.SignUp;
import com.example.restapi_java.dto.roles.AssignRoleRequest;
import com.example.restapi_java.exception.role.RoleAlreadyAssignedException;
import com.example.restapi_java.exception.role.RoleNotFoundException;
import com.example.restapi_java.exception.user.EmailAlreadyInUseException;
import com.example.restapi_java.exception.user.InvalidCredentialsException;
import com.example.restapi_java.exception.user.UserNotFoundException;
import com.example.restapi_java.model.Role;
import com.example.restapi_java.model.User;
import com.example.restapi_java.repository.RoleRepository;
import com.example.restapi_java.repository.UserRepository;
import com.example.restapi_java.service.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserService userService;


    @Test
    void shouldCreateUser_whenUserDoesNotExistAndRoleUserExists() {
        SignUp request = SignUp.builder()
                .email("john@example.com")
                .password("Password123!")
                .username("John")
                .build();

        Role mockRole = new Role();
        mockRole.setId(UUID.randomUUID());
        mockRole.setName("ROLE_USER");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(mockRole));
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");

        userService.createUser(request);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());

        User savedUser = captor.getValue();
        assertEquals(request.getEmail(), savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());
        assertEquals(request.getUsername(), savedUser.getUsername());
    }

    @Test
    void shouldThrowException_whenEmailAlreadyInUseDuringSignUp() {
        SignUp request = SignUp.builder()
                .email("john@example.com")
                .password("Password123!")
                .username("John")
                .build();

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        assertThrows(EmailAlreadyInUseException.class, () -> userService.createUser(request));
    }

    @Test
    void shouldThrowException_whenRoleUserDoesNotExistsDuringSignUp() {
        SignUp request = SignUp.builder()
                .email("john@example.com")
                .password("Password123!")
                .username("John")
                .build();

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> userService.createUser(request));
    }

    @Test
    void shouldThrowException_whenUserNotFoundDuringSignIn() {
        AuthCredentials credentials = AuthCredentials.builder()
                .email("john@example.com")
                .password("Password123!")
                .build();

        when(userRepository.findByEmail(credentials.getEmail())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.signIn(credentials));
    }

    @Test
    void shouldThrowException_whenCredentialsAreInvalidDuringSignIn() {
        AuthCredentials credentials = AuthCredentials.builder()
                .email("john@example.com")
                .password("Password123!")
                .build();

        User user = new User();
        user.setEmail(credentials.getEmail());
        user.setPassword("encodedPassword");

        when(userRepository.findByEmail(credentials.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(credentials.getPassword(), user.getPassword())).thenReturn(false);
        assertThrows(InvalidCredentialsException.class, () -> userService.signIn(credentials));
    }

    @Test
    void shouldReturnToken_whenUserExistsAndCredentialsAreValid() {
        AuthCredentials credentials = AuthCredentials.builder()
                .email("john@example.com")
                .password("Password123!")
                .build();

        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName("ROLE_USER");

        Set<Role> roles = Set.of(role);

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(credentials.getEmail());
        user.setPassword("encodedPassword");
        user.setRoles(roles);

        when(userRepository.findByEmail(credentials.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(credentials.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtService.generateToken(String.valueOf(user.getId()), Set.of("ROLE_USER"))).thenReturn("mocked-jwt-token");

        AuthResponse authResponse = userService.signIn(credentials);

        assertEquals("mocked-jwt-token", authResponse.getToken());
    }

    @Test
    void shouldThrowException_whenRoleNotFoundDuringAssignRole() {
        AssignRoleRequest request = AssignRoleRequest.builder()
                .email("john@example.com")
                .name("ROLE_USER")
                .build();

        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.empty());
        assertThrows(RoleNotFoundException.class, () -> userService.assignRole(request));
    }

    @Test
    void shouldThrowUserNotFoundException_whenUserNotExistsDuringAssignRole() {
        AssignRoleRequest request = AssignRoleRequest.builder()
                .email("john@example.com")
                .name("ROLE_USER")
                .build();

        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName("ROLE_USER");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(role));
        assertThrows(UserNotFoundException.class, () -> userService.assignRole(request));
    }

    @Test
    void shouldThrowRoleAlreadyAssignedException_whenRoleAlreadyAssignedToUserDuringAssignRole() {
        AssignRoleRequest request = AssignRoleRequest.builder()
                .email("john@example.com")
                .name("ROLE_USER")
                .build();

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(request.getEmail());
        user.setPassword("encodedPassword");

        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName("ROLE_USER");

        user.setRoles(Set.of(role));

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(role));

        assertThrows(RoleAlreadyAssignedException.class, () -> userService.assignRole(request));
    }

    @Test
    void shouldAssignRole_whenUserAndRoleExistDuringAssignRole() {
        AssignRoleRequest request = AssignRoleRequest.builder()
                .email("john@example.com")
                .name("ROLE_USER")
                .build();

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(request.getEmail());
        user.setPassword("encodedPassword");

        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName("ROLE_USER");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(role));

        userService.assignRole(request);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());

        User savedUser = captor.getValue();

        assertEquals(request.getEmail(), savedUser.getEmail());
        assertTrue(savedUser.getRoles().contains(role));
    }

    @Test
    void shouldRemoveRoleFromUsers_whenRoleAssignedToUsers() {
        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName("ROLE_USER");

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername("john");
        user.setPassword("encodedPassword");
        user.setRoles(new HashSet<>(Set.of(role)));

        List<User> users = List.of(user);

        when(userRepository.findAllByRolesContaining(role)).thenReturn(users);

        userService.unassignRoleFromAllUsers(role);

        @SuppressWarnings("unchecked")
        ArgumentCaptor<List<User>> captor = (ArgumentCaptor<List<User>>) (ArgumentCaptor<?>) ArgumentCaptor.forClass(List.class);
        verify(userRepository).saveAll(captor.capture());

        List<User> savedUsers = captor.getValue();
        assertEquals(users.size(), savedUsers.size());
        assertFalse(savedUsers.get(0).getRoles().contains(role));
    }
}

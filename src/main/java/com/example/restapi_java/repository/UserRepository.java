package com.example.restapi_java.repository;

import com.example.restapi_java.dto.UserDTO;
import com.example.restapi_java.model.Role;
import com.example.restapi_java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    List<User> findAllByRolesContaining(Role role);
}

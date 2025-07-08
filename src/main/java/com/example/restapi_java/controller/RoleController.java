package com.example.restapi_java.controller;

import com.example.restapi_java.dto.roles.RoleRequest;
import com.example.restapi_java.service.role.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/role")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class RoleController extends BaseController {
    private final RoleService roleService;

    @PostMapping("/init")
    public ResponseEntity<Void> init() {
        roleService.init();

        return CREATED();
    }

    public ResponseEntity<Void> create(@RequestBody @Valid RoleRequest request) {
        roleService.createRole(request);

        return CREATED();
    }
}

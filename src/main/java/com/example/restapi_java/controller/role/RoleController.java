package com.example.restapi_java.controller.role;

import com.example.restapi_java.controller.BaseController;
import com.example.restapi_java.dto.roles.RoleRequest;
import com.example.restapi_java.dto.roles.RoleResponse;
import com.example.restapi_java.dto.roles.UpdateRoleRequest;
import com.example.restapi_java.service.management.RoleManagementService;
import com.example.restapi_java.service.role.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class RoleController extends BaseController {
    private final RoleService roleService;
    private final RoleManagementService roleManagementService;

    @PostMapping("/init")
    public ResponseEntity<Void> init() {
        log.info("Init role controller");
        roleService.init();

        return CREATED();
    }

    @PostMapping
    public ResponseEntity<RoleResponse> create(@RequestBody @Valid RoleRequest request) {
        log.info("Creating role: {}", request.getName());
        RoleResponse role = roleService.createRole(request);

        return CREATED(role);
    }

    @GetMapping("/{name}")
    public ResponseEntity<RoleResponse> read(@PathVariable String name) {
        log.info("Reading role: {}", name);
        RoleResponse role = roleService.getRole(name);

        return OK(role);
    }

    @PatchMapping("/{name}")
    public ResponseEntity<RoleResponse> update(@PathVariable String name, @RequestBody @Valid UpdateRoleRequest request) {
        log.info("Updating role: {}", name);
        RoleResponse role = roleService.updateRole(request, name);
        return OK(role);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        log.info("Deleting role: {}", name);
        roleManagementService.deleteRoleByName(name);

        return NOCONTENT();
    }
}

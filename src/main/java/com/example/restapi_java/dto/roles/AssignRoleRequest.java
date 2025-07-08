package com.example.restapi_java.dto.roles;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class AssignRoleRequest extends RoleRequest {

    @NotBlank
    private String email;
}

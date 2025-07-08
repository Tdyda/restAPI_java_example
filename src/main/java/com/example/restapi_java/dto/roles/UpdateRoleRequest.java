package com.example.restapi_java.dto.roles;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UpdateRoleRequest {

    @NotBlank
    private String newRoleName;
}

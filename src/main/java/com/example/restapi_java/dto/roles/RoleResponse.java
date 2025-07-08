package com.example.restapi_java.dto.roles;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Builder
@Data
@EqualsAndHashCode
public class RoleResponse {
    private UUID id;
    private String name;
}

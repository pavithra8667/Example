package com.infinite.ehrSystem.role.service;

import com.infinite.ehrSystem.role.dto.RoleDto;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    RoleDto createRole(RoleDto roleDto);

    RoleDto getRoleById(UUID id);

    RoleDto getRoleByName(String name);

    List<RoleDto> getAllRoles();
}
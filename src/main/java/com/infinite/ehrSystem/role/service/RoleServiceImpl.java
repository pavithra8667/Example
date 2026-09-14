package com.infinite.ehrSystem.role.service;

import com.infinite.ehrSystem.role.dto.RoleDto;
import com.infinite.ehrSystem.role.entity.Role;
import com.infinite.ehrSystem.role.exception.DuplicateRoleException;
import com.infinite.ehrSystem.role.exception.InvalidRoleException;
import com.infinite.ehrSystem.role.exception.RoleNotFoundException;
import com.infinite.ehrSystem.role.repository.RoleRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(
            RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {

        if (roleDto.getName() == null ||
                roleDto.getName().isBlank()) {

            throw new InvalidRoleException(
                    "Role name is required"
            );
        }

        String roleName =
                roleDto.getName()
                        .trim()
                        .toUpperCase();

        if (roleRepository.existsByName(roleName)) {

            throw new DuplicateRoleException(
                    "Role already exists"
            );
        }

        Role role = new Role();

        role.setName(roleName);

        Role savedRole =
                roleRepository.save(role);

        return convertToDto(savedRole);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDto getRoleById(UUID id) {

        Role role =
                roleRepository.findById(id)
                        .orElseThrow(() ->
                                new RoleNotFoundException(
                                        "Role not found"
                                ));

        return convertToDto(role);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDto getRoleByName(String name) {

        if (name == null || name.isBlank()) {

            throw new InvalidRoleException(
                    "Role name is required"
            );
        }

        Role role =
                roleRepository.findByName(
                                name.trim().toUpperCase()
                        )
                        .orElseThrow(() ->
                                new RoleNotFoundException(
                                        "Role not found"
                                ));

        return convertToDto(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDto> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    private RoleDto convertToDto(
            Role role) {

        RoleDto dto = new RoleDto();

        dto.setId(role.getId());
        dto.setName(role.getName());

        return dto;
    }
}
package com.example.technicalassessment.mapper;

import com.example.technicalassessment.Exception.IdInvalidException;
import com.example.technicalassessment.domain.Permission;
import com.example.technicalassessment.domain.Role;
import com.example.technicalassessment.dto.role.RoleDTO;
import com.example.technicalassessment.request.role.RoleRequest;
import com.example.technicalassessment.response.role.RoleResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleMapper {

    private final PermissionMapper permissionMapper;

    public RoleMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    public Role toModel(RoleRequest roleRequest) throws IdInvalidException {
        Role role = new Role();
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());
        role.setActive(roleRequest.getActive());

        if (roleRequest.getPermissionsID() != null) {
            List<Permission> perms = roleRequest.getPermissionsID()
                    .stream()
                    .map(id -> {
                        Permission p = new Permission();
                        p.setId(id);
                        return p;
                    })
                    .toList();
            role.setPermissions(perms);
        }
        else{
            throw new IdInvalidException("Invalid Role ID");
        }
        return role;
    }

    public RoleDTO toDTO(Role role) {
        return new RoleDTO(role.getId(), role.getName(), role.getDescription(), role.getActive(), this.permissionMapper.toDTO(role.getPermissions()));
    }

    public RoleResponse toResponse(RoleDTO dto) {
        return new RoleResponse(dto.getId(), dto.getName(), dto.getDescription(), dto.isActive(), this.permissionMapper.toResponseList(dto.getPermissionDTOs()));
    }
}


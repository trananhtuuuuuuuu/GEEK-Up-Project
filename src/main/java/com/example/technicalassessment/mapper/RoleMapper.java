package com.example.technicalassessment.mapper;

import com.example.technicalassessment.Exception.IdInvalidException;
import com.example.technicalassessment.domain.Permission;
import com.example.technicalassessment.domain.Role;
import com.example.technicalassessment.dto.role.RoleDTO;
import com.example.technicalassessment.request.role.RoleCreateRequest;
import com.example.technicalassessment.request.role.RoleUpdateRequest;
import com.example.technicalassessment.response.role.RoleResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleMapper {

    private final PermissionMapper permissionMapper;

    public RoleMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    public Role toModel(RoleCreateRequest roleCreateRequest) throws IdInvalidException {

        Role role = new Role(roleCreateRequest.getName(), roleCreateRequest.getDescription(), roleCreateRequest.getActive());


        if (roleCreateRequest.getPermissionsID() != null) {
            List<Permission> perms = roleCreateRequest.getPermissionsID()
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





    public Role toModel(RoleUpdateRequest roleUpdateRequest) throws IdInvalidException {

        Role role = new Role(roleUpdateRequest.getName(),  roleUpdateRequest.getDescription(), roleUpdateRequest.getActive());

        role.setId(roleUpdateRequest.getId());

        if (roleUpdateRequest.getPermissionsID() != null) {
            List<Permission> perms = roleUpdateRequest.getPermissionsID()
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
        //return new RoleResponse(dto.getId(), dto.getName(), dto.getDescription(), dto.isActive(), this.permissionMapper.toResponseList(dto.getPermissionDTOs()));
        return new RoleResponse(dto.getId(), dto.getName());
    }
}


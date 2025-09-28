package com.example.technicalassessment.mapper.permission;

import com.example.technicalassessment.domain.Permission;
import com.example.technicalassessment.dto.permission.PermissionDTO;
import com.example.technicalassessment.request.permission.PermissionRequest;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {
    public PermissionDTO toDTO(Permission permission) {
        return new  PermissionDTO(
                permission.getName(),
                permission.getApiPath(),
                permission.getMethod(),
                permission.getModule()
        );
    }

    public Permission toModel(PermissionDTO dto) {
        return new Permission(dto.getName(), dto.getApiPath(), dto.getMethod(), dto.getModule());
    }

    public Permission toModel(PermissionRequest permissionRequest) {
        return new Permission(permissionRequest.getName(), permissionRequest.getApiPath(), permissionRequest.getMethod(), permissionRequest.getModule());
    }
}

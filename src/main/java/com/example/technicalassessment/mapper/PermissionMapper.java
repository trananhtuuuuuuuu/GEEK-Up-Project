package com.example.technicalassessment.mapper;

import com.example.technicalassessment.domain.Permission;
import com.example.technicalassessment.dto.permission.PermissionDTO;
import com.example.technicalassessment.request.permission.PermissionRequest;
import com.example.technicalassessment.response.permission.PermissionResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PermissionMapper {
    public PermissionDTO toDTO(Permission permission) {
        return new  PermissionDTO(
                permission.getId(),
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

    public List<PermissionDTO> toDTO(List<Permission> permissions) {
        List<PermissionDTO> PermissionDTOs = new ArrayList<>();
        for (Permission permission : permissions) {
            PermissionDTOs.add(toDTO(permission));
        }
        return PermissionDTOs;
    }

    public List<PermissionResponse> toResponseList(List<PermissionDTO> permissionDTOs) {
        List<PermissionResponse> permissionResponseList = new ArrayList<>();
        for (PermissionDTO permissionDTO : permissionDTOs) {
            permissionResponseList.add(toResponse(permissionDTO));
        }
        return  permissionResponseList;
    }

    public PermissionResponse toResponse(PermissionDTO  permissionDTO) {
        return new PermissionResponse(
                permissionDTO.getId(),
                permissionDTO.getName(),
                permissionDTO.getApiPath(),
                permissionDTO.getMethod(),
                permissionDTO.getModule()
        );
    }
}

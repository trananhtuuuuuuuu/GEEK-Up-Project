package com.example.technicalassessment.dto.role;

import com.example.technicalassessment.domain.Permission;
import com.example.technicalassessment.dto.permission.PermissionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Long id;
    private String name;
    private String description;
    private boolean active;
    private List<PermissionDTO> permissionDTOs;
}

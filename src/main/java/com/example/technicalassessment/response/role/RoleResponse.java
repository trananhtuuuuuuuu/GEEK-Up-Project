package com.example.technicalassessment.response.role;

import com.example.technicalassessment.response.permission.PermissionResponse;
import lombok.*;

import java.util.List;

@Getter

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {
    private Long id;
    private String name;
    //private String description;
    //private boolean active;
    //private List<PermissionResponse> permissions;
}

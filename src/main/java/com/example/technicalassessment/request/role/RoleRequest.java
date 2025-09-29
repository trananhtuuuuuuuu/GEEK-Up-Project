package com.example.technicalassessment.request.role;

import com.example.technicalassessment.request.permission.PermissionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {
    private String name;
    private String description;
    private boolean active;
    private List<Long> permissionsID;

    public boolean getActive() {
        return active;
    }
}

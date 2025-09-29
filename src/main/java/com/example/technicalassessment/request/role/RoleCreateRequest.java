package com.example.technicalassessment.request.role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleCreateRequest {
    @NotBlank(message = "required")
    private String name;
    @NotBlank(message = "required")
    private String description;

    private boolean active;

    private List<Long> permissionsID;

    public boolean getActive() {
        return active;
    }
}

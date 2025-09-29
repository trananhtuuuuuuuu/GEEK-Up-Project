package com.example.technicalassessment.request.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleUpdateRequest {


    private Long id;

    @NotBlank(message = "required")
    private String name;

    @NotBlank(message = "required")
    private String description;


    private boolean active;

    public boolean getActive() {
        return active;
    }

    private List<Long> permissionsID;

}

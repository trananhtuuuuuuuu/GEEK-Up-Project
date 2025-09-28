package com.example.technicalassessment.request.permission;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionRequest {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "name is required")
    private String apiPath;

    @NotBlank(message = "name is required")
    private String method;

    @NotBlank(message = "name is required")
    private String module;
}

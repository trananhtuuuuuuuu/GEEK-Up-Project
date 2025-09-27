package com.example.technicalassessment.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @NotBlank(message = "Name is required")
    @Size(min=1)
    private String name;

    @Email
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Phone is required")
    @Size(min=10)
    private String phone;

    @NotBlank(message = "Province is required")
    private String province;

    @NotBlank(message = "District required")
    private String district;

    @NotBlank(message = "Commune is required")
    private String commune;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "HousingType is required")
    private String housingType;

    private String role;
}

package com.example.technicalassessment.response.user;

import com.example.technicalassessment.response.role.RoleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String name;

    private String email;

    private String gender;

    private String phone;

    private String province;

    private String district;

    private String commune;

    private String address;

    private String housingType;

    private RoleResponse role;

    //private String role;
}

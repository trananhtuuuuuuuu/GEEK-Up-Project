package com.example.technicalassessment.dto.user;

import com.example.technicalassessment.domain.Role;
import com.example.technicalassessment.response.role.RoleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    private Long id;

    private String email;

    private String name;

    private Role role;


//    private String gender;
//
//    private String phone;
//
//    private String province;
//
//    private String district;
//
//    private String commune;
//
//    private String address;
//
//    private String housingType;
//
//    private String role;
}

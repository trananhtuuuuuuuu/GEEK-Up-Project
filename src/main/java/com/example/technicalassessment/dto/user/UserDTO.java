package com.example.technicalassessment.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private String gender;
    private String phone;
    private String province;
    private String district;
    private String commune;
    private String address;
    private String housingType;
    //private String role;
}

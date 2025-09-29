package com.example.technicalassessment.response.oder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderResponse {
    private String name;
    private String email;
    private String phone;
    private String province;
    private String district;
    private String commune;
    private String address;
    private String housingType;
}

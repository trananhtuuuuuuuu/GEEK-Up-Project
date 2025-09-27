package com.example.technicalassessment.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Email
    private String email;

    private String password;
    private String phone;
    private String province;
    private String district;
    private String commune;
    private String address;
    private String housingType;

    public User(String name,
                String email,
                String password,
                String phone,
                String province,
                String district,
                String commune,
                String address,
                String housingType) {
        this.name  = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.province = province;
        this.district = district;
        this.commune = commune;
        this.address = address;
        this.housingType = housingType;

    }
}

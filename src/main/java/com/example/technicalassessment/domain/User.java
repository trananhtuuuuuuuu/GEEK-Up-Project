package com.example.technicalassessment.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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
    private String email;
    private String password;
    private String gender;
    private String phone;
    private String province;
    private String district;
    private String commune;
    private String address;
    private String housingType;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    public User(String name,
                String email,
                String password,
                String gender,
                String phone,
                String province,
                String district,
                String commune,
                String address,
                String housingType) {
        this.name  = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
        this.province = province;
        this.district = district;
        this.commune = commune;
        this.address = address;
        this.housingType = housingType;

    }
}

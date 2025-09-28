package com.example.technicalassessment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="permissions")
@Getter
@Setter
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String apiPath;
    private String method;
    private String module;

    @ManyToMany(fetch=FetchType.LAZY, mappedBy = "permissions")
    @JsonIgnore
    private List<Role> roles;
}

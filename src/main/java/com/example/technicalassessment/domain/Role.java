package com.example.technicalassessment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value={"roles"})
    @JoinTable(
            name="permission_role",
            joinColumns = @JoinColumn(name="role_id"),
            inverseJoinColumns = @JoinColumn(name="permission_id"))
    private List<Permission> permissions;

}

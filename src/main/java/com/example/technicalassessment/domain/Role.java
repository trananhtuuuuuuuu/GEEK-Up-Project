package com.example.technicalassessment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean active;

    public Role(String name, String description, boolean active) {
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public boolean getActive() {
        return active;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value={"roles"})
    @JoinTable(
            name="permission_role",
            joinColumns = @JoinColumn(name="role_id"),
            inverseJoinColumns = @JoinColumn(name="permission_id"))
    private List<Permission> permissions;


    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonIgnore
    List<User> users;


}

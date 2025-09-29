package com.example.technicalassessment.service.role;


import com.example.technicalassessment.Exception.IdInvalidException;
import com.example.technicalassessment.domain.Permission;
import com.example.technicalassessment.domain.Role;
import com.example.technicalassessment.repository.PermissionRepository;
import com.example.technicalassessment.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    public RoleService(RoleRepository roleRepository,
                       PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }




    public Role createRole(Role role) throws IdInvalidException {
        if(this.roleRepository.existsByName(role.getName())){
            throw new IdInvalidException("Role name already exists");
        }

        if(role.getPermissions() != null){
            List<Long> requestPermissions = role.getPermissions()
                    .stream().map(Permission::getId)
                    .toList();

            List<Permission> DBPermissions = this.permissionRepository.findByIdIn(requestPermissions);
            role.setPermissions(DBPermissions);
        }

        return roleRepository.save(role);
    }


}

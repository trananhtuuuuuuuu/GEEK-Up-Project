package com.example.technicalassessment.service;


import com.example.technicalassessment.Exception.IdInvalidException;
import com.example.technicalassessment.domain.Permission;
import com.example.technicalassessment.domain.Role;
import com.example.technicalassessment.repository.PermissionRepository;
import com.example.technicalassessment.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public Role getRoleById(Long id) throws IdInvalidException {
        return this.roleRepository.findById(id)
                .orElseThrow(() -> new IdInvalidException("Role id not found"));
    }



    public Role updateRole(Role role) throws IdInvalidException {

        Role roleFromDB = this.roleRepository.findById(role.getId()).orElseThrow(()->new IdInvalidException("Role id not found"));

        if(role.getPermissions() != null){
            List<Long> requestPermissions = role.getPermissions()
                    .stream().map(Permission::getId)
                    .toList();

            List<Permission> DBPermissions = this.permissionRepository.findByIdIn(requestPermissions);
            role.setPermissions(DBPermissions);
        }


        assert roleFromDB != null;

        roleFromDB.setName(role.getName());
        roleFromDB.setDescription(role.getDescription());
        roleFromDB.setActive(role.getActive());
        roleFromDB.setPermissions(role.getPermissions());


        return this.roleRepository.save(roleFromDB);
    }


}

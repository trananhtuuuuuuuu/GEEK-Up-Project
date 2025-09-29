package com.example.technicalassessment.service;

import com.example.technicalassessment.Exception.IdInvalidException;
import com.example.technicalassessment.Exception.PermissionAlreadyExistsException;
import com.example.technicalassessment.domain.Permission;
import com.example.technicalassessment.repository.PermissionRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }


    public boolean isPermissionExists(Permission permission) {
        return this.permissionRepository.existsByModuleAndApiPathAndMethod(permission.getModule(), permission.getApiPath(), permission.getMethod());
    }

    public Permission savePermission(Permission permission) {
        return this.permissionRepository.save(permission);
    }

    public Permission getPermissionById(Long id) throws IdInvalidException {
        return  this.permissionRepository.findById(id).orElseThrow(
                () -> new IdInvalidException("Permission id not found")
        );
    }

    public Permission updatePermission(Long id, Permission permission) throws PermissionAlreadyExistsException {

        Permission permissionFromDB = getPermissionById(id);

        if(isPermissionExists(permission)) {
            throw new PermissionAlreadyExistsException("Permission already exists");
        }

        permissionFromDB.setName(permission.getName());
        permissionFromDB.setApiPath(permission.getApiPath());
        permissionFromDB.setMethod(permission.getMethod());
        permissionFromDB.setModule(permission.getModule());

        return permissionRepository.save(permissionFromDB);

    }

}

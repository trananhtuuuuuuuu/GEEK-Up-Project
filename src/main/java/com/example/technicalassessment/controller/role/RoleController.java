package com.example.technicalassessment.controller.role;


import com.example.technicalassessment.domain.Role;
import com.example.technicalassessment.dto.role.RoleDTO;
import com.example.technicalassessment.mapper.RoleMapper;
import com.example.technicalassessment.request.role.RoleRequest;
import com.example.technicalassessment.response.ApiResponse;
import com.example.technicalassessment.response.role.RoleResponse;
import com.example.technicalassessment.service.role.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Permissions;

@RestController
public class RoleController {
    private final RoleService roleService;
    private final RoleMapper  roleMapper;


    public RoleController(RoleService roleService,  RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }


    @PostMapping("/roles")
    public ResponseEntity<ApiResponse> createRole(@Valid @RequestBody RoleRequest roleRequest){

        ApiResponse apiResponse = new ApiResponse();

        Role role = this.roleService.createRole(
                this.roleMapper.toModel(roleRequest)
        );

        RoleDTO roleDTO = this.roleMapper.toDTO(role);
        RoleResponse roleResponse = this.roleMapper.toResponse(roleDTO);

        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setMessage("Successfully");
        apiResponse.setMetadata(roleResponse);

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(apiResponse);
    }


}

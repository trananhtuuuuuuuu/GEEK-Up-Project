package com.example.technicalassessment.controller.role;


import com.example.technicalassessment.domain.Role;
import com.example.technicalassessment.dto.role.RoleDTO;
import com.example.technicalassessment.mapper.RoleMapper;
import com.example.technicalassessment.request.role.RoleCreateRequest;
import com.example.technicalassessment.request.role.RoleUpdateRequest;
import com.example.technicalassessment.response.ApiResponse;
import com.example.technicalassessment.response.role.RoleResponse;
import com.example.technicalassessment.service.role.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    private final RoleService roleService;
    private final RoleMapper  roleMapper;


    public RoleController(RoleService roleService,  RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }


    @PostMapping("/roles")
    public ResponseEntity<ApiResponse> createRole(@Valid @RequestBody RoleCreateRequest roleCreateRequest){

        ApiResponse apiResponse = new ApiResponse();

        Role role = this.roleService.createRole(
                this.roleMapper.toModel(roleCreateRequest)
        );

        RoleDTO roleDTO = this.roleMapper.toDTO(role);
        RoleResponse roleResponse = this.roleMapper.toResponse(roleDTO);

        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setMessage("Successfully");
        apiResponse.setMetadata(roleResponse);

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(apiResponse);
    }



    @PutMapping("/roles")
    public ResponseEntity<ApiResponse> updateRole(@Valid @RequestBody RoleUpdateRequest roleUpdateRequest){

        ApiResponse apiResponse = new ApiResponse();

        Role role = this.roleService.updateRole(
                this.roleMapper.toModel(roleUpdateRequest)
        );

        RoleDTO roleDTO = this.roleMapper.toDTO(role);
        RoleResponse roleResponse = this.roleMapper.toResponse(roleDTO);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Successfully");
        apiResponse.setMetadata(roleResponse);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}

package com.example.technicalassessment.controller.permission;

import com.example.technicalassessment.Exception.PermissionAlreadyExistsException;
import com.example.technicalassessment.domain.Permission;
import com.example.technicalassessment.mapper.PermissionMapper;
import com.example.technicalassessment.request.permission.PermissionRequest;
import com.example.technicalassessment.response.ApiResponse;
import com.example.technicalassessment.response.permission.PermissionResponse;
import com.example.technicalassessment.service.permission.PermissionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PermissionController {

    private final PermissionService permissionService;
    private final PermissionMapper permissionMapper;

    public PermissionController(PermissionService permissionService,
                                PermissionMapper permissionMapper) {
        this.permissionService = permissionService;
        this.permissionMapper = permissionMapper;
    }

    @PostMapping("/permissions")
    public ResponseEntity<ApiResponse> signPermission(@Valid @RequestBody PermissionRequest permissionRequest) {

        ApiResponse apiResponse = new ApiResponse();

        if(this.permissionService.isPermissionExists(this.permissionMapper.toModel(permissionRequest))){
//            apiResponse.setMessage("Permission already exists");
//            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//            apiResponse.setError("");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
            throw new PermissionAlreadyExistsException("Permission already exists");
        }


        Permission permission = this.permissionService.savePermission(
                this.permissionMapper.toModel(permissionRequest)
        );

        PermissionResponse permissionResponse = new PermissionResponse(
                permission.getId(),
                permission.getName(),
                permission.getApiPath(),
                permission.getMethod(),
                permission.getModule()
        );

        apiResponse.setMetadata(permissionResponse);
        apiResponse.setMessage("successfully");
        apiResponse.setStatus(HttpStatus.CREATED.value());


        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/permissions")
    public ResponseEntity<ApiResponse> updatePermission(@RequestParam Long id, @Valid @RequestBody PermissionRequest permissionRequest) {

        ApiResponse apiResponse = new ApiResponse();

        Permission permission = this.permissionService.updatePermission(
                id, this.permissionMapper.toModel(permissionRequest)
        );

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}

package com.example.technicalassessment.mapper;

import com.example.technicalassessment.domain.User;
import com.example.technicalassessment.dto.user.UserDTO;
import com.example.technicalassessment.request.user.RegistrationRequest;
import com.example.technicalassessment.service.role.RoleService;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final RoleService roleService;

    public UserMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    public User toModel(UserDTO userDTO) {
        return new User(
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getGender(),
                userDTO.getPhone(),
                userDTO.getProvince(),
                userDTO.getDistrict(),
                userDTO.getCommune(),
                userDTO.getAddress(),
                userDTO.getHousingType(),
                userDTO.getRole()

        );
    }


    public UserDTO toDTO(RegistrationRequest registrationRequest) {
        return new UserDTO(
                registrationRequest.getName(),
                registrationRequest.getEmail(),
                registrationRequest.getPassword(),
                registrationRequest.getGender(),
                registrationRequest.getPhone(),
                registrationRequest.getProvince(),
                registrationRequest.getDistrict(),
                registrationRequest.getCommune(),
                registrationRequest.getAddress(),
                registrationRequest.getHousingType(),
                this.roleService.getRoleById(registrationRequest.getRoleId())
                //registrationRequest.getRole()
        );
    }


    public User toModel(RegistrationRequest registrationRequest) {
        return new User(
                registrationRequest.getName(),
                registrationRequest.getEmail(),
                registrationRequest.getPassword(),
                registrationRequest.getGender(),
                registrationRequest.getPhone(),
                registrationRequest.getProvince(),
                registrationRequest.getDistrict(),
                registrationRequest.getCommune(),
                registrationRequest.getAddress(),
                registrationRequest.getHousingType(),
                this.roleService.getRoleById(registrationRequest.getRoleId())
        );
    }



    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getGender(),
                user.getPhone(),
                user.getProvince(),
                user.getDistrict(),
                user.getCommune(),
                user.getAddress(),
                user.getHousingType(),
                user.getRole()
                //user.getRole().name()
        );
    }
}

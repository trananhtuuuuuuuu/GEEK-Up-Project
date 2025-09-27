package com.example.technicalassessment.mapper.user;

import com.example.technicalassessment.domain.User;
import com.example.technicalassessment.dto.user.UserDTO;
import com.example.technicalassessment.request.RegistrationRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
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
                userDTO.getHousingType()
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
                registrationRequest.getRole()
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
                user.getRole().name()
        );
    }
}

package com.example.technicalassessment.mapper.user;

import com.example.technicalassessment.domain.User;
import com.example.technicalassessment.dto.user.RegistrationDTO;
import jakarta.validation.constraints.Email;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toModel(RegistrationDTO registrationDTO) {
        return new User(
                registrationDTO.getName(),
                registrationDTO.getEmail(),
                registrationDTO.getPassword(),
                registrationDTO.getPhone(),
                registrationDTO.getProvince(),
                registrationDTO.getDistrict(),
                registrationDTO.getCommune(),
                registrationDTO.getAddress(),
                registrationDTO.getHousingType()
        );
    }

    public RegistrationDTO toDTO(User user) {
        return new RegistrationDTO(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getProvince(),
                user.getDistrict(),
                user.getCommune(),
                user.getAddress(),
                user.getHousingType()
        );
    }
}

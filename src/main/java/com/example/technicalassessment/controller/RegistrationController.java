package com.example.technicalassessment.controller;


import com.example.technicalassessment.domain.User;
import com.example.technicalassessment.dto.user.UserDTO;
import com.example.technicalassessment.request.RegistrationRequest;
import com.example.technicalassessment.response.user.UserResponse;
import com.example.technicalassessment.mapper.user.UserMapper;
import com.example.technicalassessment.response.ApiResponse;
import com.example.technicalassessment.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users/registration")
public class RegistrationController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegistrationController(UserService userService,
                                  UserMapper userMapper,
                                  BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = passwordEncoder;
    }


    @PostMapping
    public ResponseEntity<ApiResponse> registrationUser(@Valid @RequestBody RegistrationRequest registrationRequest) {

        ApiResponse apiResponse = new ApiResponse();

        String hashPassword = this.bCryptPasswordEncoder.encode(registrationRequest.getPassword());
        registrationRequest.setPassword(hashPassword);

        UserDTO userDTO = this.userMapper.toDTO(registrationRequest);

        User user = this.userService.registrationUser(this.userMapper.toModel(userDTO));

        UserResponse userResponse = new UserResponse(
                registrationRequest.getName(),
                registrationRequest.getEmail(),
                registrationRequest.getPhone(),
                registrationRequest.getProvince(),
                registrationRequest.getDistrict(),
                registrationRequest.getCommune(),
                registrationRequest.getAddress(),
                registrationRequest.getHousingType()
        );

        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setMessage("successfully");
        apiResponse.setMetadata(userResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}

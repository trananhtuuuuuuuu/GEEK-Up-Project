package com.example.technicalassessment.controller.user;


//import com.example.technicalassessment.domain.Role;
import com.example.technicalassessment.domain.User;
import com.example.technicalassessment.dto.user.UserDTO;
import com.example.technicalassessment.request.user.RegistrationRequest;
import com.example.technicalassessment.response.role.RoleResponse;
import com.example.technicalassessment.response.user.UserResponse;
import com.example.technicalassessment.mapper.UserMapper;
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

        User user = this.userMapper.toModel(userDTO);

//        if(registrationRequest.getRole() == null) {
//            user.setRole(Role.CUSTOMER);
//        }
//        else{
//            user.setRole(Role.valueOf(registrationRequest.getRole().toUpperCase()));
//        }

        user = this.userService.registrationUser(user);

        UserResponse userResponse = new UserResponse(
                user.getName(),
                user.getEmail(),
                user.getGender(),
                user.getPhone(),
                user.getProvince(),
                user.getDistrict(),
                user.getCommune(),
                user.getAddress(),
                user.getHousingType(),
                new RoleResponse(user.getRole().getId(), user.getRole().getName())
        );

        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setMessage("successfully");
        apiResponse.setMetadata(userResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}

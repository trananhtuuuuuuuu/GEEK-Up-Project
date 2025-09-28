package com.example.technicalassessment.controller.user;

import com.example.technicalassessment.domain.User;
import com.example.technicalassessment.mapper.user.UserMapper;
import com.example.technicalassessment.response.ApiResponse;
import com.example.technicalassessment.response.user.UserResponse;
import com.example.technicalassessment.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    //private final UserMapper userMapper;

    public AdminController(UserService userService
                         ) {
        this.userService = userService;
        //this.userMapper = userMapper;
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse> home() {
        ApiResponse apiResponse = new ApiResponse();
        List<User> users = new ArrayList<>();

        users = this.userService.getAllUsers();

        List<UserResponse>  userResponses = new ArrayList<>();

        for (User user : users) {
            userResponses.add(new UserResponse(
                    user.getName(),
                    user.getEmail(),
                    user.getGender(),
                    user.getPhone(),
                    user.getProvince(),
                    user.getDistrict(),
                    user.getCommune(),
                    user.getAddress(),
                    user.getHousingType(),
                    user.getRole().name()
            ));
        }

        apiResponse.setMessage("Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMetadata(userResponses);

        return ResponseEntity.ok(apiResponse);
    }
}

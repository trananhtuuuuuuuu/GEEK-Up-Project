package com.example.technicalassessment.controller.user;

import com.example.technicalassessment.domain.User;
import com.example.technicalassessment.dto.ResultPaginationDTO;
import com.example.technicalassessment.mapper.user.UserMapper;
import com.example.technicalassessment.response.ApiResponse;
import com.example.technicalassessment.response.user.UserResponse;
import com.example.technicalassessment.service.user.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<ApiResponse> getAllUsers(@RequestParam("current") Optional<String> currentOptional,
                                                   @RequestParam("pageSize") Optional<String> pageSizeOptional
    ) {

        String currentPage = currentOptional.orElse("");
        String pageSize = pageSizeOptional.orElse("");

        Pageable pageable = PageRequest.of(Integer.parseInt(currentPage) - 1, Integer.parseInt(pageSize));

        ApiResponse apiResponse = new ApiResponse();
        //List<User> users = new ArrayList<>();

        //users = this.userService.getAllUsers(pageable);

        ResultPaginationDTO resultPaginationDTO = this.userService.getAllUsers(pageable);

//        List<UserResponse>  userResponses = new ArrayList<>();
//
//        for (User user : users) {
//            userResponses.add(new UserResponse(
//                    user.getName(),
//                    user.getEmail(),
//                    user.getGender(),
//                    user.getPhone(),
//                    user.getProvince(),
//                    user.getDistrict(),
//                    user.getCommune(),
//                    user.getAddress(),
//                    user.getHousingType(),
//                    user.getRole().name()
//            ));
//        }

        apiResponse.setMessage("Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMetadata(resultPaginationDTO);

        return ResponseEntity.ok(apiResponse);
    }
}

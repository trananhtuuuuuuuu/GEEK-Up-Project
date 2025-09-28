package com.example.technicalassessment.controller.user;

import com.example.technicalassessment.domain.User;
import com.example.technicalassessment.dto.ResultPaginationDTO;
import com.example.technicalassessment.mapper.user.UserMapper;
import com.example.technicalassessment.response.ApiResponse;
import com.example.technicalassessment.response.user.UserResponse;
import com.example.technicalassessment.service.user.UserService;
import com.turkraft.springfilter.boot.Filter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponse> getAllUsers(@Filter Specification<User> specification,
                                                   Pageable pageable)
    {
        ApiResponse apiResponse = new ApiResponse();

        ResultPaginationDTO resultPaginationDTO = this.userService.getAllUsers(specification, pageable);

        apiResponse.setMessage("Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMetadata(resultPaginationDTO);

        return ResponseEntity.ok(apiResponse);
    }


    @DeleteMapping("/users")
    public ResponseEntity<ApiResponse> deleteUsersById(@RequestParam Long id){
        ApiResponse apiResponse = new ApiResponse();
        this.userService.deleteUserById(id);
        apiResponse.setMetadata("Successfully");
        apiResponse.setStatus(HttpStatus.ACCEPTED.value());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
    }






}

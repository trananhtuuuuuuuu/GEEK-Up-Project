package com.example.technicalassessment.controller.user;


import com.example.technicalassessment.dto.user.LoginDTO;
import com.example.technicalassessment.request.LoginRequest;
import com.example.technicalassessment.response.ApiResponse;
import com.example.technicalassessment.response.user.LoginResponse;
import com.example.technicalassessment.service.user.UserService;
import com.example.technicalassessment.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {


    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final SecurityUtil securityUtil;

    private final UserService userService;

    public AuthenticationController(AuthenticationManagerBuilder authenticationManagerBuilder,
                                    SecurityUtil securityUtil,
                                    UserService userService) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.securityUtil = securityUtil;
        this.userService = userService;
    }



    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) {
        ApiResponse apiResponse = new ApiResponse();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String access_token = this.securityUtil.createToken(authentication);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(access_token);

        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setEmail(loginRequest.getEmail());
        loginDTO.setId(this.userService.getUserByEmail(loginRequest.getEmail()).getId());
        loginDTO.setName(this.userService.getUserByEmail(loginRequest.getEmail()).getName());

        loginResponse.setUser(loginDTO);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        apiResponse.setMessage("Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        //apiResponse.setMetadata(loginRequest);
        apiResponse.setMetadata(loginResponse);


        return  ResponseEntity.ok(apiResponse);


    }
}

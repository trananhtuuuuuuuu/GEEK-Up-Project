package com.example.technicalassessment.controller.user;


import com.example.technicalassessment.Exception.IdInvalidException;
import com.example.technicalassessment.Exception.UserNotFoundException;
import com.example.technicalassessment.domain.User;
import com.example.technicalassessment.dto.user.LoginDTO;
import com.example.technicalassessment.request.LoginRequest;
import com.example.technicalassessment.response.ApiResponse;
import com.example.technicalassessment.response.user.LoginResponse;
import com.example.technicalassessment.service.user.UserService;
import com.example.technicalassessment.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.security.Security;

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
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        ApiResponse apiResponse = new ApiResponse();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
        );
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LoginResponse loginResponse = new LoginResponse();
        LoginDTO loginDTO = new LoginDTO();
        User userFromDB = userService.getUserByEmail(loginRequest.getEmail());
        loginDTO.setEmail(userFromDB.getEmail());
        loginDTO.setId(userFromDB.getId());
        loginDTO.setName(userFromDB.getName());

        loginResponse.setUser(loginDTO);


        //create access token
        String access_token = this.securityUtil.createAccessToken(authentication.getName(), loginResponse.getUser());
        loginResponse.setToken(access_token);


        //create refresh token
        String refreshToken = this.securityUtil.createRefreshToken(loginRequest.getEmail(),loginResponse);
        //update user
        this.userService.updateUserToken(refreshToken, loginRequest.getEmail());


        apiResponse.setMessage("Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMetadata(loginResponse);

        // set cookies
        ResponseCookie responseCookie = ResponseCookie
                .from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(600)
                .build();


        return  ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(apiResponse);

    }




    @GetMapping("/auth/account")
    public ResponseEntity<ApiResponse> getAccount(){

        ApiResponse apiResponse = new ApiResponse();

        String email = SecurityContextHolder.getContext().getAuthentication().getName();


        LoginResponse loginResponse = new LoginResponse();
        LoginDTO loginDTO = new LoginDTO();

        User user = this.userService.getUserByEmail(email);

        loginDTO.setEmail(email);
        loginDTO.setId(user.getId());
        loginDTO.setName(user.getName());

        loginResponse.setUser(loginDTO);

        apiResponse.setMessage("Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMetadata(loginResponse);

        return ResponseEntity.ok().body(apiResponse);

    }




    @GetMapping("/auth/refresh")
    public  ResponseEntity<ApiResponse> refreshToken(
            @CookieValue(name="refreshToken", defaultValue = "anhTuDZ") String refreshToken
    ) throws IdInvalidException{
        if(refreshToken.equals("anhTuDZ")){
            throw new IdInvalidException("Refresh Token is invalid");
        }
        //validation token
        Jwt decodedToken = this.securityUtil.validateRefreshToken(refreshToken);
        String email = decodedToken.getSubject();

        //check user by RefreshToken and email
        User userFromDB = this.userService.getUserByRefreshTokenAndEmail(refreshToken, email);

        if(userFromDB == null){
            throw new IdInvalidException("Invalid refresh token");
        }

        ApiResponse apiResponse = new ApiResponse();

        LoginResponse loginResponse = new LoginResponse();
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setEmail(userFromDB.getEmail());
        loginDTO.setId(userFromDB.getId());
        loginDTO.setName(userFromDB.getName());

        loginResponse.setUser(loginDTO);


        //create access token
        String access_token = this.securityUtil.createAccessToken(email, loginResponse.getUser());
        loginResponse.setToken(access_token);


        //create new refresh token
        String newRefreshToken = this.securityUtil.createRefreshToken(email,loginResponse);
        //update user
        this.userService.updateUserToken(newRefreshToken, email);

        //SecurityContextHolder.getContext().setAuthentication(authentication);


        // set cookies
        ResponseCookie responseCookie = ResponseCookie
                .from("refreshToken", newRefreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(600)
                .build();


        apiResponse.setMessage("Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMetadata(loginResponse);

        return  ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(apiResponse);
    }

}

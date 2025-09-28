package com.example.technicalassessment.config;

import com.example.technicalassessment.response.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component("authenticationEntryPoint")
public class CustomeAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;
    private final AuthenticationEntryPoint authenticationEntryPoint = new BearerTokenAuthenticationEntryPoint();

    public CustomeAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        this.authenticationEntryPoint.commence(request, response, authException);
        response.setContentType("application/json");
        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

        String errorMessage = Optional
                .ofNullable(authException.getCause()) // null
                .map(Throwable::getMessage)
                .orElse(authException.getMessage());

        exceptionResponse.setError(errorMessage);

        exceptionResponse.setMessege("Invalid Token");

        objectMapper.writeValue(response.getWriter(), exceptionResponse);
    }
}

package com.example.technicalassessment.Exception;

import com.example.technicalassessment.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException e) {
        ExceptionResponse  exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError("User Not Found");
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setMessege(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
}

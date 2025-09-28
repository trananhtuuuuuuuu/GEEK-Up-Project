package com.example.technicalassessment.Exception;

import com.example.technicalassessment.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(value = {
            UserNotFoundException.class,
            BadCredentialsException.class
    })
    public ResponseEntity<ExceptionResponse> handleLoginException(Exception e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError("Login Failed");
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessege("Invalid username or password");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(IdInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleIdException(IdInvalidException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError("Invalid");
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessege(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(PermissionAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleIdException(PermissionAlreadyExistsException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError("Invalid");
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessege(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException e) {
//        ExceptionResponse  exceptionResponse = new ExceptionResponse();
//        exceptionResponse.setError("User Not Found");
//        exceptionResponse.setStatus(HttpStatus.NOT_FOUND.value());
//        exceptionResponse.setMessege(e.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
//    }

}

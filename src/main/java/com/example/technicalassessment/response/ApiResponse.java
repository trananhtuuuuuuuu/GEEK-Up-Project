package com.example.technicalassessment.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
    private Integer status;
    private String error;
    private String message;
    private Object metadata;

}

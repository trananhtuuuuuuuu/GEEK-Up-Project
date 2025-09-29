package com.example.technicalassessment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Meta {
    private int page;
    private int pageSize;
    private int pages;
    private int total;
}

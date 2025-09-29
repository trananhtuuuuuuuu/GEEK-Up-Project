package com.example.technicalassessment.response.product;

import com.example.technicalassessment.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetProductResponse {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int size;
    private String color;
    private int inventory;
    private String categoryName;
}

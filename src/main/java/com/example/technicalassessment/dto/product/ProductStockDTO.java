package com.example.technicalassessment.dto.product;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockDTO {
    private Long productId;
    private String name;
    private String brand;
    private BigDecimal price;
    private int size;
    private String color;
    private Integer quantity;
}

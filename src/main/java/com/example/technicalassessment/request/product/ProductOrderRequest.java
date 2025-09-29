package com.example.technicalassessment.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductOrderRequest {
    private Long productId;
    private int quantity;
}

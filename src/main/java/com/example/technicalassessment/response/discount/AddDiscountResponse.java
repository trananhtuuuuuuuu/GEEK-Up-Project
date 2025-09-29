package com.example.technicalassessment.response.discount;

import com.example.technicalassessment.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddDiscountResponse {
    private Long productId;
    private Integer percentage;
}

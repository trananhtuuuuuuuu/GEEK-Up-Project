package com.example.technicalassessment.response.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDiscountResponse {
    private Long discountId;
    private Long productId;
    private Integer percentage;
}

package com.example.technicalassessment.request.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDiscountRequest {
    private Long discountId;
    private Long productId;
    private Integer percentage;
}

package com.example.technicalassessment.request.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddDiscountRequest {
    private Long productId;
    private Integer percentage;

}

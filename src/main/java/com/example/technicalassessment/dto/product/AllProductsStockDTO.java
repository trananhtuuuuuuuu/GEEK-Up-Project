package com.example.technicalassessment.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllProductsStockDTO {
    private String shopName;
    private List<ProductStockDTO> productStockDTOList;
}

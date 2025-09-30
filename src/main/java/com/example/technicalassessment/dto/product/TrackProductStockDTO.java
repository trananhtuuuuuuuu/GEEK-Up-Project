package com.example.technicalassessment.dto.product;


import com.example.technicalassessment.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackProductStockDTO {
    private Long ShopId;
    private String shopName;
    private ProductStockDTO product;

}

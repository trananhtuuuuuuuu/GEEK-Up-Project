package com.example.technicalassessment.request.order;

import com.example.technicalassessment.request.product.ProductOrderRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private List<ProductOrderRequest> productOrderRequests;
}

package com.example.technicalassessment.request.order;

import com.example.technicalassessment.request.product.ProductRequestOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrder {
    private List<ProductRequestOrder> productRequestOrders;
}

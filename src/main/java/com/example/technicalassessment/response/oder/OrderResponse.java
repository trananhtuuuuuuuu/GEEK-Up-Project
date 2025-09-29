package com.example.technicalassessment.response.oder;

import com.example.technicalassessment.dto.order.OrderDTO;
import com.example.technicalassessment.response.product.GetProductResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private OrderDTO order;
    private UserOrderResponse userOrder;
    private List<GetProductResponse> getProductResponses;
}

package com.example.technicalassessment.mapper;

import com.example.technicalassessment.domain.Order;
import com.example.technicalassessment.domain.Product;
import com.example.technicalassessment.domain.User;
import com.example.technicalassessment.dto.order.OrderDTO;
import com.example.technicalassessment.response.oder.OrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class OrderMapper {

    private final UserMapper userMapper;
    private final ProductMapper productMapper;

    public OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getDateOrdered(),
                order.getTotalAmount()
        );
    }

    public OrderResponse toOrderResponse(Order order, User user, List<Product> products) {
        return new OrderResponse(toDTO(order), this.userMapper.toUserOrderResponse(user), this.productMapper.toDTOs(products));
    }
}

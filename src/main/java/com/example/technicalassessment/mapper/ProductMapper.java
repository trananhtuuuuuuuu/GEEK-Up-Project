package com.example.technicalassessment.mapper;

import com.example.technicalassessment.domain.Product;
import com.example.technicalassessment.response.product.GetProductResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {
    public GetProductResponse toDTO(Product product) {
        return new GetProductResponse(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getPrice(),
                product.getSize(),
                product.getColor(),
                product.getInventory(),
                product.getCategory().getName()
        );
    }

    public List<GetProductResponse> toDTOs(List<Product> products) {
        List<GetProductResponse> getProductResponses = new ArrayList<>();
        for (Product product : products) {
            getProductResponses.add(toDTO(product));
        }
        return getProductResponses;
    }
}

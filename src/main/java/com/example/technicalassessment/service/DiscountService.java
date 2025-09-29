package com.example.technicalassessment.service;

import com.example.technicalassessment.Exception.IdInvalidException;
import com.example.technicalassessment.domain.Discount;
import com.example.technicalassessment.domain.Product;
import com.example.technicalassessment.repository.DiscountRepository;
import com.example.technicalassessment.request.discount.AddDiscountRequest;
import com.example.technicalassessment.request.discount.UpdateDiscountRequest;
import com.example.technicalassessment.response.discount.AddDiscountResponse;
import com.example.technicalassessment.response.discount.UpdateDiscountResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiscountService {
    private final DiscountRepository discountRepository;
    private final ProductService productService;



    public AddDiscountResponse addDiscount(AddDiscountRequest discountRequest) {
        AddDiscountResponse addDiscountResponse = new AddDiscountResponse();

        Product product = this.productService.getProductById(discountRequest.getProductId());

        Discount discount = new  Discount();
        discount.setProduct(product);
        discount.setPercentage(discountRequest.getPercentage());


        addDiscountResponse.setProductId(product.getId());
        addDiscountResponse.setPercentage(this.discountRepository.save(discount).getPercentage());
        return addDiscountResponse;

    }


    public UpdateDiscountResponse updateDiscount(UpdateDiscountRequest updateDiscountRequest) throws IdInvalidException {

        UpdateDiscountResponse updateDiscountResponse = new UpdateDiscountResponse();

        Product product = this.productService.getProductById(updateDiscountRequest.getProductId());
        Discount discountFromDB = this.discountRepository.findById(updateDiscountRequest.getDiscountId()).orElseThrow(
                () -> new IdInvalidException("Discount id not found")
        );

        discountFromDB.setProduct(product);
        discountFromDB.setPercentage(updateDiscountRequest.getPercentage());

        updateDiscountResponse.setProductId(product.getId());
        updateDiscountResponse.setPercentage(this.discountRepository.save(discountFromDB).getPercentage());
        updateDiscountResponse.setDiscountId(discountFromDB.getId());

        return updateDiscountResponse;
    }
}

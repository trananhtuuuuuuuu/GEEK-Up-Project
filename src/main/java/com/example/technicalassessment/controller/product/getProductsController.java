package com.example.technicalassessment.controller.product;

import com.example.technicalassessment.domain.Product;
import com.example.technicalassessment.dto.ResultPaginationDTO;
import com.example.technicalassessment.response.ApiResponse;
import com.example.technicalassessment.service.ProductService;
import com.turkraft.springfilter.boot.Filter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class getProductsController {

    private final ProductService productService;

    public getProductsController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public ResponseEntity<ApiResponse> getAllProducts(@Filter Specification<Product> specification,
                                                      Pageable pageable) {

        ApiResponse apiResponse = new ApiResponse();

        ResultPaginationDTO resultPaginationDTO = this.productService.getAllProducts(specification, pageable);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Successfully");
        apiResponse.setMetadata(resultPaginationDTO);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}

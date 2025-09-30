package com.example.technicalassessment.controller;



import com.example.technicalassessment.dto.product.AllProductsStockDTO;
import com.example.technicalassessment.dto.product.TrackProductStockDTO;
import com.example.technicalassessment.request.discount.AddDiscountRequest;
import com.example.technicalassessment.request.discount.UpdateDiscountRequest;
import com.example.technicalassessment.response.ApiResponse;
import com.example.technicalassessment.response.discount.AddDiscountResponse;
import com.example.technicalassessment.response.discount.UpdateDiscountResponse;
import com.example.technicalassessment.service.DiscountService;
import com.example.technicalassessment.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final DiscountService discountService;

    private final ProductService productService;

    @PostMapping("/discounts")
    @PreAuthorize("hasAuthority('Add Discount')")
    public ResponseEntity<ApiResponse> addDiscount(@RequestBody AddDiscountRequest addDiscountRequest) {
        ApiResponse apiResponse = new ApiResponse();

        AddDiscountResponse addDiscountResponse = this.discountService.addDiscount(addDiscountRequest);

        apiResponse.setMetadata(addDiscountResponse);
        apiResponse.setMessage("Successfully");
        apiResponse.setStatus(HttpStatus.CREATED.value());

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }



    @PutMapping("/discounts")
    @PreAuthorize("hasAuthority('Update Discount')")
    public ResponseEntity<ApiResponse> updateDiscount(@RequestBody UpdateDiscountRequest updateDiscountRequest) {
        ApiResponse apiResponse = new ApiResponse();

        UpdateDiscountResponse updateDiscountResponse = this.discountService.updateDiscount(updateDiscountRequest);
        apiResponse.setMetadata(updateDiscountResponse);
        apiResponse.setMessage("Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }



    @GetMapping("/tracking")
    @PreAuthorize("hasAnyAuthority('Track Product')")
    public ResponseEntity<ApiResponse> getTracking(@RequestParam Long productId, @RequestParam Long shopId) {
        ApiResponse apiResponse = new ApiResponse();

        List<TrackProductStockDTO> trackProductStockDTOS = this.productService.getStockInOtherStores(productId, shopId);

        apiResponse.setMetadata(trackProductStockDTOS);
        apiResponse.setMessage("Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());


        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }



    @GetMapping("/trackingProducts")
    @PreAuthorize("hasAnyAuthority('Track Product')")
    public ResponseEntity<ApiResponse> trackAllProductStock() {
        ApiResponse apiResponse = new ApiResponse();
        List<AllProductsStockDTO> trackProductStockDTOS = this.productService.getAllProductStock();
        apiResponse.setMetadata(trackProductStockDTOS);
        apiResponse.setMessage("Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }




}

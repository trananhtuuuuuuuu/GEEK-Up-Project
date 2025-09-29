package com.example.technicalassessment.controller.order;


import com.example.technicalassessment.request.order.CreateOrderRequest;
import com.example.technicalassessment.response.ApiResponse;
import com.example.technicalassessment.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("orders")
    public ResponseEntity<ApiResponse> placeOrder(@RequestBody CreateOrderRequest createOrderRequest){

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully");
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setMetadata(this.orderService.placeOrder(createOrderRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

}

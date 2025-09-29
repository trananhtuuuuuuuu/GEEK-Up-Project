package com.example.technicalassessment.service;

import com.example.technicalassessment.domain.Order;
import com.example.technicalassessment.domain.OrderDetail;
import com.example.technicalassessment.domain.Product;
import com.example.technicalassessment.domain.User;
import com.example.technicalassessment.mapper.OrderMapper;
import com.example.technicalassessment.repository.OrderDetailRepository;
import com.example.technicalassessment.repository.OrderRepository;
import com.example.technicalassessment.repository.ProductRepository;
import com.example.technicalassessment.repository.UserRepository;
import com.example.technicalassessment.request.order.CreateOrder;

import com.example.technicalassessment.request.product.ProductRequestOrder;
import com.example.technicalassessment.response.oder.OrderResponse;
import lombok.AllArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;
    private final UserService userService;


    public OrderResponse placeOrder(CreateOrder createOrder){
        OrderResponse orderResponse = new OrderResponse();


        List<Long> productIds = createOrder.getProductRequestOrders()
                .stream()
                .map(ProductRequestOrder::getProductId)
                .toList();

        List<Product> products = productRepository.findByIdIn(productIds);


        Map<Long,Integer> map = createOrder.getProductRequestOrders()
                .stream()
                .collect(Collectors.toMap(ProductRequestOrder::getProductId, ProductRequestOrder::getQuantity));


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.userService.getUserByEmail(email);


        Order order = new Order();
        order.setUser(user);
        order.setDateOrdered(LocalDate.now().toString());

        BigDecimal total = new BigDecimal(0);
        List<OrderDetail> details = new ArrayList<>();

        for(Product product : products){
            int quantity = map.get(product.getId());
            BigDecimal lineTotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            total = total.add(lineTotal);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(product);
            orderDetail.setQuantity(quantity);
            orderDetail.setUnitPrice(product.getPrice());
            orderDetail.setShop(product.getProductShops().getFirst().getShop());
            details.add(orderDetail);

        }

        order.setOrderDetails(details);
        order.setTotalAmount(total);

        Order createdOrder = this.orderRepository.save(order);


        orderResponse = this.orderMapper.toOrderResponse(
                createdOrder, user, products);


        return orderResponse;
    }
}

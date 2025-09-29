package com.example.technicalassessment.repository;

import com.example.technicalassessment.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {

}

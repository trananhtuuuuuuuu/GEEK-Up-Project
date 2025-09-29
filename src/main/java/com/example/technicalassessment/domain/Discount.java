package com.example.technicalassessment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "discounts")
@Getter
@Setter
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer percentage;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}

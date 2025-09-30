package com.example.technicalassessment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="product_shop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="shop_id")
    private Shop shop;

    @Column(name="quantity")
    private Integer quantity;
}

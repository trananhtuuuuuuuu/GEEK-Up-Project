package com.example.technicalassessment.domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="shop_name")
    private String name;

    @Column(name="shop_address")
    private String address;

    @Column(name="date_original")
    private LocalDate dateOriginal;

    public int getOriginalMonth() {
        return dateOriginal != null ? dateOriginal.getMonthValue() : 0;
    }

    public int getOriginalYear() {
        return dateOriginal != null ? dateOriginal.getYear() : 0;
    }

    public Shop(String name,
                String address,
                LocalDate Date){
        this.name = name;
        this.address = address;
        this.dateOriginal = Date;
    }

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<ProductShop> productShops;


    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<CartDetail> cartDetails;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;


}

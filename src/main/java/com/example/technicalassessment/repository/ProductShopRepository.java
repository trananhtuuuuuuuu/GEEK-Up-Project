package com.example.technicalassessment.repository;

import com.example.technicalassessment.domain.ProductShop;
import com.example.technicalassessment.dto.product.TrackProductStockDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface ProductShopRepository extends JpaRepository<ProductShop, Long> {

    @Query("""
    select new com.example.technicalassessment.dto.product.TrackProductStockDTO(
       ps.shop.id,
       ps.shop.name,
       new com.example.technicalassessment.dto.product.ProductStockDTO(
           ps.product.id,
           ps.product.name,
           ps.product.brand,
           ps.product.price,
           ps.product.size,
           ps.product.color,
           ps.product.inventory
       )
    )
    from ProductShop ps
    where ps.product.id = :productId
      and (:shopId is null or ps.shop.id <> :shopId)
    order by ps.quantity desc
""")
    List<TrackProductStockDTO> findStockByProductInOtherShops(
            @Param("productId") Long productId,
            @Param("shopId") Long shopId);



    @Query("""
    select new com.example.technicalassessment.dto.product.TrackProductStockDTO(
       ps.shop.id,
       ps.shop.name,
       new com.example.technicalassessment.dto.product.ProductStockDTO(
           ps.product.id,
           ps.product.name,
           ps.product.brand,
           ps.product.price,
           ps.product.size,
           ps.product.color,
           ps.product.inventory
       )
    )
    from ProductShop ps
    """)
    List<TrackProductStockDTO> findAllProductStockInAllShops();




}



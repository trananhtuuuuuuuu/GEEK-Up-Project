package com.example.technicalassessment.service;

import com.example.technicalassessment.Exception.IdInvalidException;
import com.example.technicalassessment.domain.Product;
import com.example.technicalassessment.dto.Meta;
import com.example.technicalassessment.dto.ResultPaginationDTO;
import com.example.technicalassessment.dto.product.AllProductsStockDTO;
import com.example.technicalassessment.dto.product.ProductStockDTO;
import com.example.technicalassessment.dto.product.TrackProductStockDTO;
import com.example.technicalassessment.mapper.ProductMapper;
import com.example.technicalassessment.repository.ProductRepository;
import com.example.technicalassessment.repository.ProductShopRepository;
import com.example.technicalassessment.response.product.GetProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductShopRepository productShopRepository;

    public ResultPaginationDTO getAllProducts(Specification<Product> specification, Pageable pageable){

        Page<Product> product = this.productRepository.findAll(specification, pageable);


        Meta mt = new Meta(product.getNumber() + 1, product.getSize(), product.getTotalPages(), (int) product.getTotalElements());

        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO(mt, product.getContent());


        List<GetProductResponse> productResponses = new ArrayList<>();
        for (Product p : product.getContent()) {
            GetProductResponse getProductResponse = new GetProductResponse();
            productResponses.add(this.productMapper.toDTO(p));
        }

        resultPaginationDTO.setResult(productResponses);

        return resultPaginationDTO;
    }


    public Product getProductById(Long id) throws IdInvalidException {
        return this.productRepository.findById(id).orElseThrow(
                () -> new IdInvalidException("Product with id " + id + " does not exist")
        );
    }

    public List<TrackProductStockDTO> getStockInOtherStores(Long productId, Long shopId) {
        return this.productShopRepository.findStockByProductInOtherShops(productId, shopId);
    }


    public List<AllProductsStockDTO> getAllProductStock() {
        List<TrackProductStockDTO> trackList = productShopRepository.findAllProductStockInAllShops();

        Map<String, List<ProductStockDTO>> grouped =
                trackList.stream()
                        .collect(Collectors.groupingBy(
                                TrackProductStockDTO::getShopName,
                                Collectors.mapping(TrackProductStockDTO::getProduct, Collectors.toList())
                        ));

        return grouped.entrySet().stream()
                .map(e -> new AllProductsStockDTO(e.getKey(), e.getValue()))
                .toList();
    }



}

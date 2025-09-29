package com.example.technicalassessment.service;

import com.example.technicalassessment.Exception.IdInvalidException;
import com.example.technicalassessment.domain.Product;
import com.example.technicalassessment.dto.Meta;
import com.example.technicalassessment.dto.ResultPaginationDTO;
import com.example.technicalassessment.mapper.ProductMapper;
import com.example.technicalassessment.repository.ProductRepository;
import com.example.technicalassessment.response.product.GetProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

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


//    public Product getProductById(Long id) throws IdInvalidException {
//        Product product = new Product();
//        if(this.productRepository.existsById(id)){
//            product = this.productRepository.findById(id).orElseThrow(
//                    () -> new IdInvalidException("Product with id " + id + " does not exist")
//            );
//        }
//        else{
//
//        }
//    }

}

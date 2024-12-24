package com.example.nobsv2.product.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nobsv2.Query;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;

@Service
public class GetProductsService implements Query<Void, List<ProductDTO>> {
    private final ProductRepository productRepository;

    public GetProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<List<ProductDTO>> execute(Void input) {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = products.stream().map(ProductDTO::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
    }
}

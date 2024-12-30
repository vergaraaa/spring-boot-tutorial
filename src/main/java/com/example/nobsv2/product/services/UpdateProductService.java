package com.example.nobsv2.product.services;

import java.util.Optional;

import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nobsv2.Command;
import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.UpdateProductCommand;
// import com.example.nobsv2.product.validators.ProductValidator;

@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO> {
    private final ProductRepository productRepository;

    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Override
    // @CacheEvict(value = "productCache", key = "#command.getId()") // key must match with line 28
    // evict throws away the value of the cache 
    // put throws it away and then puts the return value of the method in the cache
    @CachePut(value = "productCache", key = "#command.getId()") // key must match with line 28
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand command) {
        Optional<Product> productOptional = productRepository.findById(command.getId());

        if(productOptional.isPresent()) {
            Product product = command.getProduct();
            product.setId(command.getId());

            // ProductValidator.execute(product);

            productRepository.save(product);

            return ResponseEntity.ok(new ProductDTO(product));
        }

        throw new ProductNotFoundException();
    }
}

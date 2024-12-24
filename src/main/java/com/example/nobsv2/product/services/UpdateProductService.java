package com.example.nobsv2.product.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nobsv2.Command;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.UpdateProductCommand;

@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO> {
    private final ProductRepository productRepository;

    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Override
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand command) {
        Optional<Product> productOptional = productRepository.findById(command.getId());

        if(productOptional.isPresent()) {
            Product product = command.getProduct();
            product.setId(command.getId());
            productRepository.save(product);

            return ResponseEntity.ok(new ProductDTO(product));
        }

        return null;
    }
}

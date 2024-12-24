package com.example.nobsv2.product;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.services.CreateProductService;
import com.example.nobsv2.product.services.DeleteProductService;
import com.example.nobsv2.product.services.GetProductsService;
import com.example.nobsv2.product.services.UpdateProductService;

@RestController
public class ProductController {

    private final GetProductsService getProductsService;

    private final CreateProductService createProductService;
    
    private final UpdateProductService updateProductService;
     
    private final DeleteProductService deleteProductService;

    public ProductController(
        GetProductsService getProductsService, 
        CreateProductService createProductService,
        UpdateProductService updateProductService, 
        DeleteProductService deleteProductService
    ) {
        this.getProductsService = getProductsService;
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
        this.deleteProductService = deleteProductService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return getProductsService.execute(null);
    }

    @PostMapping
    public ResponseEntity<String> createProduct() {
        return createProductService.execute(null);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct() {
        return updateProductService.execute(null);    
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct() {
        return deleteProductService.execute(null);
    }
}

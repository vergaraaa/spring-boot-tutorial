package com.example.nobsv2.product;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final GetProductService getProductService;

    private final CreateProductService createProductService;
    
    private final UpdateProductService updateProductService;
     
    private final DeleteProductService deleteProductService;

    public ProductController(
        GetProductService getProductService, 
        CreateProductService createProductService,
        UpdateProductService updateProductService, 
        DeleteProductService deleteProductService
    ) {
        this.getProductService = getProductService;
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
        this.deleteProductService = deleteProductService;
    }

    @GetMapping
    public ResponseEntity<String> getProduct() {
        return getProductService.execute(null);
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

package com.example.nobsv2.product;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.nobsv2.exceptions.SearchProductService;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.UpdateProductCommand;
import com.example.nobsv2.product.services.CreateProductService;
import com.example.nobsv2.product.services.DeleteProductService;
import com.example.nobsv2.product.services.GetProductService;
import com.example.nobsv2.product.services.GetProductsService;
import com.example.nobsv2.product.services.UpdateProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ProductController {

    private final GetProductsService getProductsService;

    private final GetProductService getProductService;

    private final SearchProductService searchProductService;

    private final CreateProductService createProductService;
    
    private final UpdateProductService updateProductService;
     
    private final DeleteProductService deleteProductService;

    public ProductController(
        GetProductsService getProductsService, 
        GetProductService getProductService, 
        SearchProductService searchProductService,
        CreateProductService createProductService,
        UpdateProductService updateProductService, 
        DeleteProductService deleteProductService
    ) {
        this.getProductsService = getProductsService;
        this.getProductService = getProductService;
        this.searchProductService = searchProductService;
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
        this.deleteProductService = deleteProductService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return getProductsService.execute(null);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        return getProductService.execute(id);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<ProductDTO>> searchProductByName(@RequestParam String name) {
        return searchProductService.execute(name);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        return createProductService.execute(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
        @PathVariable Integer id,
        @RequestBody Product product
    ) {
        return updateProductService.execute(new UpdateProductCommand(id, product));    
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        return deleteProductService.execute(id);
    }

    // @ExceptionHandler(ProductNotFoundException.class)
    // public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException exception) {
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage()));
    // }
}

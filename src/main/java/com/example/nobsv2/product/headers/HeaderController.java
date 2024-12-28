package com.example.nobsv2.product.headers;

import org.springframework.web.bind.annotation.RestController;

import com.example.nobsv2.product.model.Product;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
public class HeaderController {
    @GetMapping("/header")
    public String getRegionalResponse(@RequestHeader(required = false, defaultValue = "US") String region) {
        if(region.equals("US")) return "Welcome to the US store!";
        
        if(region.equals("CAN")) return "Welcome to the Canada store!";

        return "Country not supported";
    }

    @GetMapping(value = "/header/product", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> getProduct () {
        Product product = new Product();
        product.setId(1);
        product.setName("Product 1");
        product.setPrice(100.0);
        product.setDescription("This is product 1 and it is awesome!");

        return ResponseEntity.ok(product);
    }
    
}

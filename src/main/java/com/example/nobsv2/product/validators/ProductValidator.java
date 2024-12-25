package com.example.nobsv2.product.validators;

import org.springframework.util.StringUtils;

import com.example.nobsv2.exceptions.ErrorMessages;
import com.example.nobsv2.exceptions.ProductNotValidException;
import com.example.nobsv2.product.model.Product;

public class ProductValidator {
    private ProductValidator() {}
    
    public static void execute(Product product) {
        if(!StringUtils.hasText(product.getName())) {
            throw new ProductNotValidException(ErrorMessages.NAME_REQUIRED.getMessage());
        }

        if(product.getDescription().length() < 20) {
            throw new ProductNotValidException(ErrorMessages.DESCRIPTION_LENGTH.getMessage());
        }

        if(product.getPrice() < 0) {
            throw new ProductNotValidException(ErrorMessages.PRICE_CANNOT_BE_NEGATIVE.getMessage());
        }
    }
}

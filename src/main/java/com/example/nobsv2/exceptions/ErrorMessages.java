package com.example.nobsv2.exceptions;

public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product Not Found"),
    NAME_REQUIRED("Product name is required"),
    DESCRIPTION_LENGTH("Product description must be at least 20 characters"),
    PRICE_CANNOT_BE_NEGATIVE("Product price cannot be negative");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

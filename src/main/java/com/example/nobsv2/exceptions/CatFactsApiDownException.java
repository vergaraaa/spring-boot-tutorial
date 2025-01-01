package com.example.nobsv2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class CatFactsApiDownException extends RuntimeException {
    public CatFactsApiDownException() {
        super(ErrorMessages.CAT_FACTS_API_DOWN.getMessage());
    }
}

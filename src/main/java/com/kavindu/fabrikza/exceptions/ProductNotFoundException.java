package com.kavindu.fabrikza.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String formatted) {
        super(ErrorMessages.PRODUCT_NOT_FOUND.getMessage());
    }
}

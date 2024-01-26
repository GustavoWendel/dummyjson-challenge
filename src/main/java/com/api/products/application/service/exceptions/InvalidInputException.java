package com.api.products.application.service.exceptions;

public class InvalidInputException extends IllegalArgumentException {

    public InvalidInputException(String message) {
        super(message);
    }
}

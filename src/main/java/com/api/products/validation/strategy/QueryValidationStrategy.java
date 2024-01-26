package com.api.products.validation.strategy;

import com.api.products.application.service.exceptions.InvalidInputException;

public class QueryValidationStrategy implements ValidationStrategy<String> {

    @Override
    public void validationStrategy(String query) {
        if (query == null || query.trim().isEmpty()) {
            throw new InvalidInputException("Consulta inválida");
        }
    }
}

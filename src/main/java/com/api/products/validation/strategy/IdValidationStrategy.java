package com.api.products.validation.strategy;

import com.api.products.application.service.exceptions.InvalidInputException;

public class IdValidationStrategy implements ValidationStrategy<Integer>{
    @Override
    public void validationStrategy(Integer id) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("ID inválido");
        }
    }
}

package com.api.products.validation.strategy;

public interface ValidationStrategy<T> {
    void validationStrategy(T input);
}

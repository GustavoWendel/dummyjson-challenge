package com.api.products.validation.strategy;

public class ValidationContext<T> {

    private final ValidationStrategy<T> validationStrategy;

    public ValidationContext(ValidationStrategy<T> validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public void executeValidation(T input) {
        validationStrategy.validationStrategy(input);
    }
}

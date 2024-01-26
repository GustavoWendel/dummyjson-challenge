package com.api.products.application.service;

import com.api.products.application.service.exceptions.NotFoundException;
import com.api.products.infra.clients.ProductFeignClient;
import com.api.products.response.ProductResponse;
import com.api.products.response.ProductResponseWrapper;
import com.api.products.validation.strategy.QueryValidationStrategy;
import com.api.products.validation.strategy.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductFeignClient productFeignClient;


    public ProductResponseWrapper getAllProducts(Integer skip, Integer limit) {
        ProductResponseWrapper responseWrapper = productFeignClient.getAll(skip, limit);

        if (responseWrapper == null) {
            throw new IllegalStateException("A resposta do serviço externo está nula.");
        }

        if (responseWrapper.getProducts() == null) {
            throw new IllegalStateException("A lista de produtos na resposta está nula.");
        }

        return responseWrapper;
    }

    public ProductResponse findById(Integer id) {
        ProductResponse response = productFeignClient.findById(id);

        if (response == null || response.getId() == null) {
            throw new NotFoundException("Produto não encontrado");
        }

        return response;
    }

    public ProductResponseWrapper searchProducts(String query, Integer skip, Integer limit) {
        ValidationContext<String> context = new ValidationContext<>(new QueryValidationStrategy());
        context.executeValidation(query);

        ProductResponseWrapper responseWrapper = productFeignClient.searchProducts(query, skip, limit);

        if (responseWrapper == null || responseWrapper.getProducts() == null) {
            throw new NotFoundException("Produtos não encontrados");
        }

        return responseWrapper;
    }
}

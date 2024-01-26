package com.api.products.application.resources.impl;

import com.api.products.application.resources.ProductControllerApi;
import com.api.products.application.service.ProductService;
import com.api.products.response.ProductResponse;
import com.api.products.response.ProductResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
public class ProductController implements ProductControllerApi {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ProductResponseWrapper> getAll(Integer skip, Integer limit) {
        ProductResponseWrapper responseWrapper = productService.getAllProducts(skip, limit);
        return ResponseEntity.ok(responseWrapper);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> getById(Integer id) {
        ProductResponse product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/search")
    public ResponseEntity<ProductResponseWrapper> search(String query, Integer skip, Integer limit) {
        ProductResponseWrapper responseWrapper = productService.searchProducts(query, skip, limit);
        return ResponseEntity.ok(responseWrapper);
    }
}

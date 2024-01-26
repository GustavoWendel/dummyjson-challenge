package com.api.products.application.resources;

import com.api.products.application.service.ProductService;
import com.api.products.response.ProductResponse;
import com.api.products.response.ProductResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        String message = "Hello, World!";
        return ResponseEntity.ok(message);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> getById(@PathVariable Integer id) {
        ProductResponse product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/search")
    public ResponseEntity<ProductResponseWrapper> searchProducts(@RequestParam("q") String query) {
        ProductResponseWrapper responseWrapper = productService.searchProducts(query);
        return ResponseEntity.ok(responseWrapper);
    }
}

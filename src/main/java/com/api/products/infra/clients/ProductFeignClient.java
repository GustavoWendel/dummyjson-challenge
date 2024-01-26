package com.api.products.infra.clients;

import com.api.products.response.ProductResponse;
import com.api.products.response.ProductResponseWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "products", url = "https://dummyjson.com")
public interface ProductFeignClient {

    @GetMapping("/products")
    ProductResponseWrapper getAll(@RequestParam(value = "skip", defaultValue = "0") Integer skip,
                                  @RequestParam(value = "limit", defaultValue = "30") Integer limit);

    @GetMapping("/products/{id}")
    ProductResponse findById(@PathVariable Integer id);

    @GetMapping("/products/search")
    ProductResponseWrapper searchProducts(
            @RequestParam("q") String query,
            @RequestParam(value = "skip", defaultValue = "0") Integer skip,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    );
}

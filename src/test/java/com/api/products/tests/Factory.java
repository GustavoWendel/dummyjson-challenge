package com.api.products.tests;

import com.api.products.response.ProductResponse;
import com.api.products.response.ProductResponseWrapper;

import java.time.Instant;
import java.util.List;

public class Factory {

    public static final List<String> images = List.of("https://cdn.dummyjson.com/product-images/1/1.jpg",
            "https://cdn.dummyjson.com/product-images/1/2.jpg",
            "https://cdn.dummyjson.com/product-images/1/3.jpg",
            "https://cdn.dummyjson.com/product-images/1/4.jpg",
            "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg");

    public static ProductResponse createProductResponse() {
        return new ProductResponse(1,
                "iPhone 9",
                "An apple mobile which is nothing like apple",
                549.0,
                12.96,
                4.69,
                94.0,
                "Apple",
                "smartphones",
                "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
                images);
    }

    public static ProductResponseWrapper createProductResponseWrapper() {
        return new ProductResponseWrapper(List.of(createProductResponse()),
                4,
                0,
                4);
    }
}

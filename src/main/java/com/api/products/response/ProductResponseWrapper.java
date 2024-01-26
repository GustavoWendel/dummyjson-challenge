package com.api.products.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProductResponseWrapper {

    @JsonProperty("products")
    private List<ProductResponse> products;
    @JsonProperty("total")
    private int total;
    @JsonProperty("skip")
    private int skip;
    @JsonProperty("limit")
    private int limit;

    public ProductResponseWrapper(List<ProductResponse> products, int total, int skip, int limit) {
        this.products = products;
        this.total = total;
        this.skip = skip;
        this.limit = limit;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}

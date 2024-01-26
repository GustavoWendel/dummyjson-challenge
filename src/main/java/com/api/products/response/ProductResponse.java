package com.api.products.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {

    private Integer id;
    private String title;
    private String description;
    private Double price;
    private Double discountPercentage;
    private Double rating;
    private Double stock;
    private String brand;
    private String category;
    private String thumbnail;

    @JsonProperty("images")
    List<String> images = new ArrayList<> ();

    public ProductResponse(Integer id, String title, String description, Double price, Double discountPercentage, Double rating, Double stock, String brand, String category, String thumbnail, List<String> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.rating = rating;
        this.stock = stock;
        this.brand = brand;
        this.category = category;
        this.thumbnail = thumbnail;
        this.images = images;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public Double getRating() {
        return rating;
    }

    public Double getStock() {
        return stock;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponse that = (ProductResponse) o;
        return Double.compare(that.price, price) == 0 && Double.compare(that.discountPercentage, discountPercentage) == 0 && Double.compare(that.rating, rating) == 0 && Double.compare(that.stock, stock) == 0 && Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(brand, that.brand) && Objects.equals(category, that.category) && Objects.equals(thumbnail, that.thumbnail) && Objects.equals(images, that.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, price, discountPercentage, rating, stock, brand, category, thumbnail, images);
    }
}

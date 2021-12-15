package com.onlinestore.controllers.dto;

import com.onlinestore.entities.Category;
import com.onlinestore.entities.Product;
import com.onlinestore.services.CategoryService;

public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String imageURL;
    private double price;
    private long stock;
    private long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public static Product toProduct(ProductDto productDto, CategoryService categoryService){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setStock(productDto.getStock());
        product.setPrice(productDto.getPrice());

        Category category = categoryService.findById(productDto.getCategoryId());
        product.setCategory(category);
        return product;
    }

    public static ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageURL(product.getImageURL());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setStock(product.getStock());
        productDto.setId(product.getId());
        return productDto;
    }
}

package com.onlinestore.controllers.dto;

import com.onlinestore.entities.Product;
import com.onlinestore.entities.ProductQuantity;

public class ProductQuantityDto {

    private Long id;
    private ProductDto productDto;
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static ProductQuantityDto toProductQuantityDto(ProductQuantity productQuantity) {
        ProductQuantityDto productQuantityDto = new ProductQuantityDto();

        productQuantityDto.setId(productQuantity.getId());
        productQuantityDto.setProductDto(ProductDto.toProductDto(productQuantity.getProduct()));
        productQuantityDto.setQuantity(productQuantity.getQuantity());
        return productQuantityDto;
    }
}

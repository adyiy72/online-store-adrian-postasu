package com.onlinestore.controllers.dto;

import com.onlinestore.entities.Product;
import com.onlinestore.entities.ProductQuantity;
import com.onlinestore.entities.ShoppingCart;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartDto {

    private List<ProductQuantityDto> productQuantityDtos;

    private Double totalPrice;

    public List<ProductQuantityDto> getProductQuantityDtos() {
        return productQuantityDtos;
    }

    public void setProductQuantityDtos(List<ProductQuantityDto> productQuantityDtos) {
        this.productQuantityDtos = productQuantityDtos;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static ShoppingCartDto toShoppingCartDto(ShoppingCart shoppingCart) {
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        List<ProductQuantityDto> productQuantityDtoList =
                shoppingCart
                        .getProductsInCart()
                        .stream()
                        .map(ProductQuantityDto::toProductQuantityDto)
                        .collect(Collectors.toList());
        shoppingCartDto.setProductQuantityDtos(productQuantityDtoList);

        return shoppingCartDto;
    }
}

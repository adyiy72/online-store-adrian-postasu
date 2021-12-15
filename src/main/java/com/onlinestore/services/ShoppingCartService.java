package com.onlinestore.services;

import com.onlinestore.controllers.dto.ProductQuantityDto;
import com.onlinestore.entities.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart addProductToCart(Long productId);
    ShoppingCart removeProductFromCart(Long productId);
    ShoppingCart getShoppingCartOfLoggedUser();
    ShoppingCart resetShoppingCart();
    ShoppingCart update(ProductQuantityDto productQuantityDto);

}

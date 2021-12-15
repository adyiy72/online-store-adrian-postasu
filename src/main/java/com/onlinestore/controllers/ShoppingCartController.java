package com.onlinestore.controllers;

import com.onlinestore.controllers.dto.ProductQuantityDto;
import com.onlinestore.controllers.dto.ShoppingCartDto;
import com.onlinestore.entities.ShoppingCart;
import com.onlinestore.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/shopping-cart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PutMapping
    public ShoppingCartDto addProduct(@RequestBody Long productId) {
        ShoppingCart shoppingCart = shoppingCartService.addProductToCart(productId);
        return ShoppingCartDto.toShoppingCartDto(shoppingCart);
    }

    @DeleteMapping(path = "/products/{productId}")
    public ShoppingCartDto deleteProduct(@PathVariable Long productId){
        ShoppingCart shoppingCart = shoppingCartService.removeProductFromCart(productId);
        return ShoppingCartDto.toShoppingCartDto(shoppingCart);
    }

    @GetMapping
    public ShoppingCartDto getShoppingCart(){
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartOfLoggedUser();
        return ShoppingCartDto.toShoppingCartDto(shoppingCart);
    }

    @PostMapping(path = "/update")
    public ShoppingCartDto updateProductQuantityInShoppingCart(@RequestBody ProductQuantityDto productQuantityDto){
        ShoppingCart shoppingCart = shoppingCartService.update(productQuantityDto);
        return ShoppingCartDto.toShoppingCartDto(shoppingCart);
    }

}

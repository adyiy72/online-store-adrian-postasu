package com.onlinestore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    @OneToMany
    private List<ProductQuantity> productsInCart = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ProductQuantity> getProductsInCart() {
        return productsInCart;
    }

    public void setProductsInCart(List<ProductQuantity> productsInCart) {
        this.productsInCart = productsInCart;
    }
}



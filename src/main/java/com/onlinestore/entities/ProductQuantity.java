package com.onlinestore.entities;

import com.onlinestore.entities.Product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ProductQuantity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Product product;

    private int quantity;

    public ProductQuantity(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public ProductQuantity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

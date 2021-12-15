package com.onlinestore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.criteria.Expression;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String imageURL;
    private double price = 0L;
    private Long stock = 0L;
    @ManyToOne
    private Category category;
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
    public void setStock(Long stock) {
        this.stock = stock;
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
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}

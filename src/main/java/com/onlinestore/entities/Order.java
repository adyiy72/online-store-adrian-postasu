package com.onlinestore.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity(name = "user_orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    private double total;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductOrderCopy> productOrderCopyList;

    @CreationTimestamp
    private Date date;

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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ProductOrderCopy> getProductOrderCopyList() {
        return productOrderCopyList;
    }

    public void setProductOrderCopyList(List<ProductOrderCopy> productOrderCopyList) {
        this.productOrderCopyList = productOrderCopyList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

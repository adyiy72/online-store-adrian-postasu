package com.onlinestore.controllers.dto;

import com.onlinestore.entities.Order;
import com.onlinestore.entities.ProductOrderCopy;
import java.util.Date;
import java.util.List;

public class OrderDto {
    private Long id;
    private double total;
    private List<ProductOrderCopy> productOrderCopyList;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public static OrderDto toOrderDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setDate(order.getDate());
        orderDto.setId(order.getId());
        orderDto.setProductOrderCopyList(order.getProductOrderCopyList());
        orderDto.setTotal(order.getTotal());
        return orderDto;
    }
}

package com.onlinestore.services;

import com.onlinestore.entities.Order;

import java.util.List;

public interface OrderService {

    Order save();
    List<Order> getLoggedUserOrders();

}

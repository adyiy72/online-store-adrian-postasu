package com.onlinestore.controllers;

import com.onlinestore.controllers.dto.OrderDto;
import com.onlinestore.entities.Order;
import com.onlinestore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public OrderDto createOrder() {
        return OrderDto.toOrderDto(orderService.save());
    } //de modificat sa primeasca adressa de id in paramtrul de intrare

    @GetMapping
    public List<OrderDto> getAllOrders(){
        return orderService.getLoggedUserOrders().stream()
                .map(el -> OrderDto.toOrderDto(el)).collect(Collectors.toList());
    }


}

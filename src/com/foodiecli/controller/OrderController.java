package com.foodiecli.controller;

import com.foodiecli.exceptions.OrderExistsException;
import com.foodiecli.exceptions.OrderNotFoundException;
import com.foodiecli.model.Order;
import com.foodiecli.service.OrderServiceImp;

import java.util.List;

public class OrderController {
    private final OrderServiceImp orderService;

    public OrderController(OrderServiceImp orderService) {
        this.orderService = orderService;
    }

    public List<Order> getOrdersList(){
        return this.orderService.getOrderList();
    }

    public Order getOrderById(String id) throws OrderNotFoundException {
        return this.orderService.getOrderById(id);
    }

    public Order saveOrder(Order order) throws OrderExistsException {
        return this.orderService.saveOrder(order);
    }

    public Order updateOrder(Order order) throws OrderNotFoundException{
        return this.orderService.updateOrder(order);
    }

    public void deleteOrder(String id) throws OrderNotFoundException{
        this.orderService.deleteOrder(id);
    }
}

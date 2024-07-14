package com.foodiecli.service;

import com.foodiecli.exceptions.OrderExistsException;
import com.foodiecli.exceptions.OrderNotFoundException;
import com.foodiecli.model.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getOrderList();

    public Order getOrderById(String id) throws OrderNotFoundException;

    public Order saveOrder(Order order) throws OrderExistsException;

    public Order updateOrder(Order order) throws OrderNotFoundException;

    public void deleteOrder(String id) throws OrderNotFoundException;

}

package com.foodiecli.service;

import com.foodiecli.exceptions.OrderExistsException;
import com.foodiecli.exceptions.OrderNotFoundException;
import com.foodiecli.model.Order;
import com.foodiecli.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrderList() {
        return this.orderRepository.getOrderList();
    }

    @Override
    public Order getOrderById(String id) throws OrderNotFoundException {
        Optional<Order> orderById = this.orderRepository.findOrderById(id);
        if (orderById.isEmpty())
            throw new OrderNotFoundException("Order Not Found With Id :" + id);
        return orderById.get();
    }

    @Override
    public Order saveOrder(Order order) throws OrderExistsException {
        Optional<Order> saveOrder = this.orderRepository.findOrderById(order.getOrderId());
        if (saveOrder.isPresent())
            throw new OrderExistsException("Order Already Exists with this Id :" + order.getOrderId());
        return this.orderRepository.saveOrder(order);
    }

    @Override
    public Order updateOrder(Order orderToBeUpdated) throws OrderNotFoundException {
        Optional<Order> updateOrder = this.orderRepository.findOrderById(orderToBeUpdated.getOrderId());
        if (updateOrder.isEmpty())
            throw new OrderNotFoundException("Order Not Found With Id :" + orderToBeUpdated.getOrderId());
        return this.orderRepository.updateOrder(orderToBeUpdated);
    }

    @Override
    public void deleteOrder(String id) throws OrderNotFoundException {
        Optional<Order> deleteOrder = this.orderRepository.findOrderById(id);
        if (deleteOrder.isEmpty())
            throw new OrderNotFoundException("Order Not Found With Id :" + id);
        this.orderRepository.deleteOrder(deleteOrder.get());
    }
}

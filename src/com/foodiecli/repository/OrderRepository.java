package com.foodiecli.repository;

import com.foodiecli.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {
    private List<Order> orderList;

    public OrderRepository() {
        this.orderList = new ArrayList<>();
    }

    public Order saveOrder(Order order){
        this.orderList.add(order);
        return order;
    }

    public List<Order> getOrderList(){
        return this.orderList;
    }

    public Optional<Order> findOrderById(String id){
        return this.orderList.stream()
                             .filter(order -> order.getOrderId().equals(id))
                             .findFirst();

    }

    public Order updateOrder(Order orderToBeUpdated) {
        Optional<Order> optionalOrder = this.orderList.stream()
                .filter(order -> order.getOrderId().equals(orderToBeUpdated.getOrderId()))
                .findFirst()
                .map(order -> {
                    order.setCustomer(orderToBeUpdated.getCustomer())
                            .setRestaurant(orderToBeUpdated.getRestaurant())
                            .setOrderDate(orderToBeUpdated.getOrderDate())
                            .setOrderDishes(orderToBeUpdated.getOrderDishes())
                            .setOrderPrice(orderToBeUpdated.getOrderPrice());
                    return order;
                });
        return optionalOrder.orElse(null);
    }

    public void deleteOrder(Order order) {
        this.orderList.remove(order);
    }
}
